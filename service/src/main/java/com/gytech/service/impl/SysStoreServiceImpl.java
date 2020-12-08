package com.gytech.service.impl;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gytech.Const;
import com.gytech.LocalEntity.Res;
import com.gytech.LocalEntity.ResultInfo;
import com.gytech.Utils.AESUtil;
import com.gytech.Utils.DBUtil;
import com.gytech.Utils.GU;
import com.gytech.Utils.LogUtil;
import com.gytech.entity.admin.SysFile;
import com.gytech.entity.admin.SysLog;
import com.gytech.entity.admin.SysStore;
import com.gytech.mapper.admin.SysStoreMapper;
import com.gytech.service.ISysFileService;
import com.gytech.service.ISysLogService;
import com.gytech.service.ISysStoreService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LQ
 * @since 2018-12-03
 */
@Service
public class SysStoreServiceImpl extends ServiceImpl<SysStoreMapper, SysStore> implements ISysStoreService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${path.sql}")
    private String sqlPath;

    @Value("${path.mysqlhome}")
    private String mysqlhome;

    @Autowired
    private ISysLogService logService;

    @Autowired
    private ISysFileService fileService;

    @Override
    public boolean add(SysStore sysStore) {
        String key = AESUtil.encrypt(sysStore.getInstancePass(), Const.AES_KEY);
        sysStore.setInstancePass(key);
        return save(sysStore);
    }

    @Async
    @Override
    public Res backupStore(Long id) {
        Res res = new Res();
        SysStore sysStore = getById(id);
        if (sysStore==null){
            return res.reason(ResultInfo.INFO_NOT_FIND);
        }
        String ip = sysStore.getIp();
        String port = sysStore.getPort();
        String user = sysStore.getInstanceUser();
        String pass = AESUtil.decrypt(sysStore.getInstancePass(),Const.AES_KEY);
        String db = sysStore.getDatabaseName();
        String type= sysStore.getInstanceType();
        String fileName = GU.getStringDateSeconds() + db;
        if (type.equalsIgnoreCase(Const.MYSQL)){
            res = DBUtil.exportDatabaseToolMysql(mysqlhome,ip,port,user,pass,sqlPath,fileName + ".sql" ,db);
        }else if (type.equalsIgnoreCase(Const.ORACLE)){
            res = DBUtil.exportDatabaseToolOracle(user,pass,ip,port,db,sqlPath,fileName);
        }
        SysLog log;
        if (res.getSuccess()){
            SysFile file = new SysFile();
            file.setCreateAt(new Date());
            File sql = new File(String.valueOf(res.getData()));
            if (!sql.exists()){
                log = LogUtil.failedLog(LogUtil.LOG_TYPE_SQLBACKUP,"数据库：" + db,"",null);
                logService.save(log);
                return res.reason("导出数据失败");
            }
            log = LogUtil.createLogByEvent(LogUtil.LOG_TYPE_SQLBACKUP,"数据库：" + db,String.valueOf(res.getData()),null);
            logService.save(log);
            file.setPath(String.valueOf(res.getData()));
            file.setName(sql.getName());
            file.setType(Const.FILE_TYPE_SQLBACKUP);
            fileService.save(file);
        }else {
            log = LogUtil.failedLog(LogUtil.LOG_TYPE_SQLBACKUP,"数据库：" + db,"",null);
            logService.save(log);
            return res.reason("导出数据失败");
        }
        return res.success().data("");
    }


    @Override
    public Res connect(Long id) {
        Res res = new Res();
        if (id==null){
            return res.reason(ResultInfo.INFO_NOT_FIND);
        }
        SysStore sysStore = getById(id);
        if (sysStore == null){
            return res.reason(ResultInfo.INFO_NOT_FIND);
        }
        String pass = AESUtil.decrypt(sysStore.getInstancePass(),Const.AES_KEY);
        Connection conn = null;
        Properties props =new Properties();
        String url = "";
        if (sysStore.getInstanceType().equalsIgnoreCase("mysql")){
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return res.reason("com.mysql.jdbc.Driver" + "not found");
            }
            url ="jdbc:mysql://" + sysStore.getIp() + ":" + sysStore.getPort() + "/" + sysStore.getDatabaseName() + "?useUnicode=true&characterEncoding=UTF8";
            props.setProperty("user", sysStore.getInstanceUser());props.setProperty("password", pass);
            props.setProperty("remarks","false"); //设置可以获取remarks信息
            props.setProperty("useInformationSchema","false");//设置可以获取tables remarks信息
        }else if (sysStore.getInstanceType().equalsIgnoreCase("oracle")){
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return res.reason("oracle.jdbc.driver.OracleDriver" + "not found");
            }
            url ="jdbc:oracle:thin:@" + sysStore.getIp() + ":" + sysStore.getPort() +":" + sysStore.getDatabaseName();
            props.setProperty("user", sysStore.getInstanceUser());
            props.setProperty("password", pass);
            props.setProperty("remarks","false");
        }else {
            return res.reason(ResultInfo.INFO_UNSUPPORTED);
        }
        try {
            DriverManager.setLoginTimeout(3);//3s
            conn = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            e.printStackTrace();
            return res.reason(url + " 连接失败：" + e.getErrorCode() + ";" + e.getSQLState());
        }finally {
            try {
                if (conn!=null)
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return res.success();
    }
    
	@Autowired
	private TaskScheduler taskScheduler;
	@Value("${database.backup.interval}")
	private int backupInterval;
	private CountDownLatch latch = new CountDownLatch(1);
	@SuppressWarnings("deprecation")
	//@PostConstruct
    public void autoBackupStore() {
    	StringBuilder builder = new StringBuilder()
    			.append("0")
    			.append(" 0")
    			.append(" 23")
    			.append(" */").append(String.valueOf(backupInterval))
    			.append(" *")
    			.append(" ?");
    	CronTrigger trigger = new CronTrigger(builder.toString());
    	taskScheduler.schedule(new Runnable() {
    		
			@Override
			public void run() {
				databaseBackup();
				
			}
		}, trigger);
    	if(new Date().getHours() > 23) {
    		long wait = 10*60*1000;
    		long waitUntil = System.currentTimeMillis()+wait*60*1000;
    		try {
				latch.await(wait, TimeUnit.MICROSECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		if(waitUntil - System.currentTimeMillis() < 0 ) {
    			databaseBackup();
    		}
    	}
	}
	
	private void databaseBackup() {
	    logger.info("Trigger databaseBackup");
		latch.countDown();
		QueryWrapper<SysStore> wrapper = new QueryWrapper<>();
		List<SysStore> sysStores = baseMapper.selectList(null);
		for(SysStore store : sysStores) {
		    if (store.getActiveTime()!=null && store.getActiveTime() > 0){
                backupStore(store.getId());
            }
		}
	}
    
}
