/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.7.20 : Database - sow
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sow` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `sow`;

/*Table structure for table `change` */

DROP TABLE IF EXISTS `change`;

CREATE TABLE `change` (
  `id` varchar(32) NOT NULL,
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `del` tinyint(1) DEFAULT '0' COMMENT '被删除',
  `type` varchar(50) DEFAULT NULL COMMENT '变更类型（海洋站、中心站、XXX）',
  `oid` varchar(32) DEFAULT NULL COMMENT '对象id',
  `change` text COMMENT '变更内容（json对象{name：value}）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='变更表（海洋站、中心站变更记录；房屋维护记录）';

/*Data for the table `change` */

/*Table structure for table `equip_check_record` */

DROP TABLE IF EXISTS `equip_check_record`;

CREATE TABLE `equip_check_record` (
  `id` varchar(32) NOT NULL,
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `del` tinyint(1) DEFAULT '0' COMMENT '被删除',
  `equip` varchar(32) DEFAULT NULL COMMENT '设备id',
  `op_user` bigint(20) DEFAULT NULL COMMENT '操作人',
  `op_time` datetime DEFAULT NULL,
  `option` varchar(1000) DEFAULT NULL COMMENT '所做操作',
  `complete` tinyint(1) DEFAULT '0' COMMENT '是否完成',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='设备检定记录';

/*Data for the table `equip_check_record` */

/*Table structure for table `equip_repair` */

DROP TABLE IF EXISTS `equip_repair`;

CREATE TABLE `equip_repair` (
  `id` varchar(32) NOT NULL,
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `del` tinyint(1) DEFAULT '0' COMMENT '被删除',
  `occur_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '故障时间',
  `end_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修复时间',
  `type` varchar(50) DEFAULT NULL COMMENT '故障类型',
  `work_status` varchar(50) DEFAULT NULL COMMENT '工作状态',
  `repair_status` varchar(50) DEFAULT NULL COMMENT '维修状态',
  `description` varchar(255) DEFAULT NULL COMMENT '维修描述',
  `instance` varchar(50) DEFAULT NULL COMMENT '流程',
  `equip_id` varchar(32) DEFAULT NULL COMMENT '装备id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='设备维修';

/*Data for the table `equip_repair` */

/*Table structure for table `ground_radar_soft` */

DROP TABLE IF EXISTS `ground_radar_soft`;

CREATE TABLE `ground_radar_soft` (
  `id` varchar(32) NOT NULL,
  `grid` varchar(32) NOT NULL,
  `RSGRName` varchar(50) DEFAULT NULL,
  `RSGREdition` varchar(50) DEFAULT NULL,
  `RSGRDeveloper` varchar(50) DEFAULT NULL,
  `RSGRAgency` varchar(50) DEFAULT NULL,
  `RSGRDataProduct` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `ground_radar_soft` */

/*Table structure for table `laxs_record` */

DROP TABLE IF EXISTS `laxs_record`;

CREATE TABLE `laxs_record` (
  `id` varchar(32) NOT NULL,
  `recuser_id` bigint(20) DEFAULT NULL COMMENT '上报人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '接收时间',
  `rec_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '上报时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `del` tinyint(1) DEFAULT '0' COMMENT '被删除',
  `soid` bigint(20) DEFAULT NULL COMMENT '站点id',
  `rec_lon` decimal(6,6) DEFAULT NULL COMMENT '上报经度',
  `rec_lat` decimal(6,6) DEFAULT NULL COMMENT '上报纬度',
  `rec_num` varchar(10) DEFAULT NULL COMMENT '上报期数',
  `wind_direct` varchar(10) DEFAULT NULL COMMENT '风向（NSEW）',
  `wind_speed` decimal(6,6) DEFAULT NULL COMMENT '风速 米每秒',
  `visibility` decimal(6,6) DEFAULT NULL COMMENT '能见度 km',
  `mount` tinyint(2) DEFAULT NULL COMMENT '绿潮量 成',
  `drift_direct` varchar(255) DEFAULT NULL COMMENT '漂移方向',
  `detail` varchar(500) DEFAULT NULL COMMENT '巡视及绿潮情况描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='陆岸巡视';

/*Data for the table `laxs_record` */

/*Table structure for table `log` */

DROP TABLE IF EXISTS `log`;

CREATE TABLE `log` (
  `id` varchar(32) NOT NULL,
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `del` tinyint(1) DEFAULT '0' COMMENT '被删除',
  `ltype` varchar(50) DEFAULT NULL COMMENT '日志类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='地波雷达';

/*Data for the table `log` */

/*Table structure for table `observe_detail` */

DROP TABLE IF EXISTS `observe_detail`;

CREATE TABLE `observe_detail` (
  `id` varchar(32) NOT NULL,
  `oid` varchar(50) DEFAULT NULL COMMENT '所属机构',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `install_time` varchar(50) DEFAULT NULL COMMENT '系统安装时间',
  `install_location` varchar(100) DEFAULT NULL COMMENT '系统安装地点',
  `install_position` varchar(100) DEFAULT NULL COMMENT '安装位置',
  `lon` varchar(4) DEFAULT NULL COMMENT '平台观测系统经度',
  `lat` decimal(6,6) DEFAULT NULL COMMENT '平台观测系统纬度',
  `subordinate_unit` varchar(50) DEFAULT NULL COMMENT '平台观测系统所属部门',
  `obser_sea` varchar(50) DEFAULT NULL COMMENT '系统监测范围',
  `platform_type` varchar(50) DEFAULT NULL COMMENT '系统类型',
  `obser_element` varchar(50) DEFAULT NULL COMMENT '系统观测要素',
  `comType` varchar(50) DEFAULT NULL COMMENT '系统通信方式',
  `start_time` varchar(50) DEFAULT NULL COMMENT '系统启用时间',
  `end_time` varchar(50) DEFAULT NULL COMMENT '系统停用时间',
  `transmission_type` varchar(50) DEFAULT NULL COMMENT '数据传输方式',
  `data_storage` varchar(50) DEFAULT NULL COMMENT '系统数据存储方式',
  `picture` varchar(255) DEFAULT NULL COMMENT '平台观测系统照片',
  `remark` varchar(1000) DEFAULT NULL COMMENT '平台观测系统备注',
  `del` tinyint(1) DEFAULT NULL,
  `geo` text COMMENT '附属图形范围',
  `frequency` varchar(255) DEFAULT NULL COMMENT '频率',
  `spa_resolution` varchar(255) DEFAULT NULL COMMENT '空间分辨率',
  `azi_resolution` varchar(255) DEFAULT NULL COMMENT '方位分辨率',
  `callsign` varchar(255) DEFAULT NULL COMMENT '船号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='观测站点详细 信息';

/*Data for the table `observe_detail` */

/*Table structure for table `sensor` */

DROP TABLE IF EXISTS `sensor`;

CREATE TABLE `sensor` (
  `id` varchar(32) NOT NULL,
  `oid` varchar(32) NOT NULL,
  `otype` varchar(50) DEFAULT NULL COMMENT '所属类型',
  `name` varchar(50) DEFAULT NULL COMMENT '传感器名称',
  `type` varchar(50) DEFAULT NULL COMMENT '传感器类型',
  `manufacture` varchar(100) DEFAULT NULL COMMENT '生产商',
  `installedTime` varchar(50) DEFAULT NULL COMMENT '安装时间',
  `obserCheckTime` datetime DEFAULT NULL COMMENT '监测时间',
  `obserCheckPeriod` int(10) DEFAULT NULL COMMENT '检定周期 日',
  `obserPeriod` varchar(50) DEFAULT NULL COMMENT '观测周期说明',
  `location` varchar(50) DEFAULT NULL,
  `comType` varchar(50) DEFAULT NULL,
  `obserElement` varchar(200) DEFAULT NULL,
  `dataFrequency` varchar(100) DEFAULT NULL,
  `startedTime` datetime DEFAULT NULL,
  `endTime` varchar(50) DEFAULT NULL,
  `pictures` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sensor` */

/*Table structure for table `station_duty_record` */

DROP TABLE IF EXISTS `station_duty_record`;

CREATE TABLE `station_duty_record` (
  `id` varchar(32) NOT NULL,
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `del` tinyint(1) DEFAULT '0' COMMENT '被删除',
  `log` varchar(1000) DEFAULT NULL COMMENT '值班日志',
  `user_id` varchar(32) DEFAULT NULL COMMENT '值班人员id',
  `start_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `end_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `sid` varchar(32) DEFAULT NULL COMMENT '站点id',
  `stype` varchar(50) DEFAULT NULL COMMENT '站点类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='交接班记录';

/*Data for the table `station_duty_record` */

/*Table structure for table `station_duty_user` */

DROP TABLE IF EXISTS `station_duty_user`;

CREATE TABLE `station_duty_user` (
  `id` varchar(32) NOT NULL,
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `del` tinyint(1) DEFAULT '0' COMMENT '被删除',
  `oid` varchar(32) DEFAULT NULL,
  `user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='值班人员（应是用户的子集）';

/*Data for the table `station_duty_user` */

/*Table structure for table `station_equit` */

DROP TABLE IF EXISTS `station_equit`;

CREATE TABLE `station_equit` (
  `id` varchar(32) NOT NULL COMMENT '观测项目标识号',
  `otype` varchar(50) DEFAULT '所属机构类型',
  `oid` varchar(50) DEFAULT '机构id',
  `name` varchar(100) DEFAULT NULL COMMENT '设备名称',
  `equipments` varchar(100) DEFAULT NULL COMMENT '观测项目仪器设备',
  `manufacturer` varchar(100) DEFAULT NULL COMMENT '观测项目设备生产厂家',
  `type` varchar(100) DEFAULT NULL COMMENT '项目设备型号',
  `status` varchar(10) DEFAULT NULL COMMENT '观测项目设备状态',
  `startTime` varchar(50) DEFAULT NULL COMMENT '观测项目开始时间',
  `obserCheckTime` datetime DEFAULT NULL COMMENT '仪器设备检定时间',
  `obserCheckPeriod` int(10) DEFAULT NULL COMMENT '仪器设备检定周期（天）',
  `obserMethod` varchar(200) DEFAULT NULL COMMENT '观测项目观测方法',
  `startToStopTime` text COMMENT '观测项目观测记录起止时间',
  `location` varchar(100) DEFAULT NULL COMMENT '位置',
  `obserLon` decimal(8,5) DEFAULT NULL,
  `obserLat` decimal(8,5) DEFAULT NULL,
  `tideHeight` varchar(100) DEFAULT NULL COMMENT '观测项目潮高基准面高程/测波仪海拔高度',
  `tideDepth` varchar(100) DEFAULT NULL COMMENT '观测项目测波浮标（传感器）处潮高基准面小水深',
  `distance` varchar(100) DEFAULT NULL COMMENT '观测项目测波浮标（传感器）距观测场地水平距离',
  `obserHeigth` varchar(100) DEFAULT NULL COMMENT '观测项目观测场/点海拔高度',
  `windHeight` varchar(100) DEFAULT NULL COMMENT '观测项目风传感器离地高度',
  `obserPlatHeight` varchar(100) DEFAULT NULL COMMENT '观测项目观测平台离地高度',
  `gasHeight` varchar(100) DEFAULT NULL COMMENT '观测项目气压传感器海拔高度',
  `comType` varchar(100) DEFAULT NULL COMMENT '观测项目资料传输方式',
  `exeStandard` text COMMENT '观测项目执行规范',
  `projectType` varchar(100) DEFAULT NULL COMMENT '观测项目类型',
  `pictures` varchar(255) DEFAULT NULL COMMENT '观测项目照片（文件id）',
  `positionDescription` varchar(100) DEFAULT NULL,
  `height` varchar(100) DEFAULT NULL,
  `wellPH` varchar(100) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='观测设备表';

/*Data for the table `station_equit` */

/*Table structure for table `station_event` */

DROP TABLE IF EXISTS `station_event`;

CREATE TABLE `station_event` (
  `id` varchar(32) NOT NULL,
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `del` tinyint(1) DEFAULT '0' COMMENT '被删除',
  `occur_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `brief` text COMMENT '简要信息',
  `file` varchar(255) DEFAULT NULL,
  `file_type` varchar(50) DEFAULT NULL COMMENT '文件类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='站点大事记';

/*Data for the table `station_event` */

/*Table structure for table `station_floor` */

DROP TABLE IF EXISTS `station_floor`;

CREATE TABLE `station_floor` (
  `id` varchar(32) NOT NULL,
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `del` tinyint(1) DEFAULT '0' COMMENT '被删除',
  `house_id` varchar(32) DEFAULT NULL COMMENT '房屋id',
  `height` varchar(50) DEFAULT NULL COMMENT '层高',
  `roomnum` tinyint(4) DEFAULT NULL COMMENT '房间数',
  `plan` varchar(50) DEFAULT NULL COMMENT '规划图',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='站点楼层';

/*Data for the table `station_floor` */

/*Table structure for table `station_fund` */

DROP TABLE IF EXISTS `station_fund`;

CREATE TABLE `station_fund` (
  `id` varchar(32) NOT NULL,
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `del` tinyint(1) DEFAULT '0' COMMENT '被删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='站点固定资产';

/*Data for the table `station_fund` */

/*Table structure for table `station_house` */

DROP TABLE IF EXISTS `station_house`;

CREATE TABLE `station_house` (
  `id` varchar(32) NOT NULL COMMENT '海洋站房屋标识号',
  `otype` varchar(50) DEFAULT NULL COMMENT '单位类型',
  `oid` varchar(32) NOT NULL,
  `HousesOrganization` varchar(50) DEFAULT NULL COMMENT '海洋站房屋单位名称',
  `HousesUseage` varchar(200) DEFAULT NULL COMMENT '海洋站房屋名称及用途',
  `HousesLocation` varchar(200) DEFAULT NULL COMMENT '海洋站房屋位置',
  `HousesArea` varchar(50) DEFAULT NULL COMMENT '海洋站房屋面积',
  `HousesStructureType` varchar(50) DEFAULT NULL COMMENT '海洋站房屋建筑形式',
  `HousesEndTime` varchar(50) DEFAULT NULL COMMENT '海洋站房屋竣工时间',
  `HousesInputTime` varchar(50) DEFAULT NULL COMMENT '海洋站房屋投入使用时间',
  `HousesStatus` text COMMENT '海洋站房屋现状',
  `HousesCertificateNum` varchar(50) DEFAULT NULL COMMENT '海洋站房屋证书号码',
  `HousesCertificateTime` varchar(50) DEFAULT NULL COMMENT '海洋站房屋发证时间',
  `HousesBookValue` varchar(50) DEFAULT NULL COMMENT '海洋站房屋账面价值',
  `HousesPlan` varchar(64) DEFAULT NULL COMMENT '海洋站房屋平面图',
  `HousesSectionPlan` varchar(64) DEFAULT NULL COMMENT '海洋站房屋剖面图',
  `HousesStereogram` varchar(64) DEFAULT NULL COMMENT '海洋站房屋立体图',
  `HousesMemo` text COMMENT '备注',
  `create_user_id` bigint(20) DEFAULT NULL,
  `update_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(255) DEFAULT NULL,
  `del` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='海洋站房屋';

/*Data for the table `station_house` */

/*Table structure for table `station_land` */

DROP TABLE IF EXISTS `station_land`;

CREATE TABLE `station_land` (
  `id` varchar(32) NOT NULL,
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `del` tinyint(1) DEFAULT '0' COMMENT '被删除',
  `organization` varchar(255) DEFAULT NULL COMMENT '所属单位',
  `location` varchar(255) DEFAULT NULL COMMENT '位置',
  `certification` varchar(255) DEFAULT NULL COMMENT '证书编号',
  `area` varchar(255) DEFAULT NULL COMMENT '面积',
  `getdate` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '取得时间',
  `bookvalue` varchar(255) DEFAULT NULL COMMENT '整数标号',
  `building_area` varchar(255) DEFAULT NULL COMMENT '建筑面积',
  `readlineplan` varchar(50) DEFAULT NULL COMMENT '规划文件',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='站点房屋';

/*Data for the table `station_land` */

/*Table structure for table `station_observe` */

DROP TABLE IF EXISTS `station_observe`;

CREATE TABLE `station_observe` (
  `id` varchar(32) NOT NULL,
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注说明',
  `del` tinyint(1) DEFAULT '0' COMMENT '被删除',
  `equipid` varchar(32) DEFAULT NULL COMMENT '观测项目名称',
  `name` varchar(255) DEFAULT NULL,
  `oid` bigint(20) DEFAULT NULL COMMENT '所属站点',
  `period` varchar(50) DEFAULT NULL COMMENT '观测周期（corn）',
  `method` text COMMENT '观测方法',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='站点观测项目';

/*Data for the table `station_observe` */

/*Table structure for table `station_sealevel` */

DROP TABLE IF EXISTS `station_sealevel`;

CREATE TABLE `station_sealevel` (
  `id` varchar(32) NOT NULL,
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `del` tinyint(1) DEFAULT '0' COMMENT '被删除',
  `oid` varchar(32) DEFAULT NULL COMMENT '海洋站id',
  `otype` varchar(50) DEFAULT NULL COMMENT '站点类型',
  `req_param` varchar(255) DEFAULT NULL COMMENT '请求参数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='站点潮位预报表';

/*Data for the table `station_sealevel` */

/*Table structure for table `station_standard` */

DROP TABLE IF EXISTS `station_standard`;

CREATE TABLE `station_standard` (
  `id` varchar(32) NOT NULL,
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `del` tinyint(1) DEFAULT '0' COMMENT '被删除',
  `oid` bigint(11) DEFAULT NULL,
  `otype` varchar(50) DEFAULT NULL COMMENT '机构类型',
  `name` varchar(255) DEFAULT NULL,
  `no` varchar(50) DEFAULT NULL COMMENT '编号',
  `type` varchar(255) DEFAULT NULL COMMENT '标准类型',
  `filetype` varchar(255) DEFAULT NULL COMMENT '文件类型',
  `issueddepart` varchar(255) DEFAULT NULL COMMENT '颁发部门',
  `issueddate` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '颁发时间',
  `file` varchar(255) DEFAULT NULL COMMENT '文件',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='计量标准';

/*Data for the table `station_standard` */

/*Table structure for table `station_task` */

DROP TABLE IF EXISTS `station_task`;

CREATE TABLE `station_task` (
  `id` varchar(32) NOT NULL COMMENT '任务名称',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '任务描述',
  `del` tinyint(1) DEFAULT '0' COMMENT '被删除',
  `name` varchar(255) DEFAULT NULL COMMENT '任务名称',
  `detail` varchar(255) DEFAULT NULL COMMENT '任务描述',
  `period` varchar(255) DEFAULT NULL COMMENT '任务周期',
  `oid` bigint(20) DEFAULT NULL COMMENT '所属机构',
  `method` varchar(255) DEFAULT NULL COMMENT '任务流程说明',
  `task_instance` varchar(255) DEFAULT NULL COMMENT '对应的流程',
  `task_type` varchar(255) DEFAULT NULL COMMENT '任务类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='站点任务';

/*Data for the table `station_task` */

/*Table structure for table `station_task_record` */

DROP TABLE IF EXISTS `station_task_record`;

CREATE TABLE `station_task_record` (
  `id` varchar(32) NOT NULL,
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `del` tinyint(1) DEFAULT '0' COMMENT '被删除',
  `instance` varchar(64) DEFAULT NULL COMMENT '流程id',
  `taskid` varchar(32) DEFAULT NULL COMMENT '关联taskid',
  `complete` tinyint(1) DEFAULT NULL COMMENT '是否完成',
  `isflow` tinyint(1) DEFAULT NULL COMMENT '是否是流程任务',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='站点任务记录';

/*Data for the table `station_task_record` */

/*Table structure for table `underseaobr_node` */

DROP TABLE IF EXISTS `underseaobr_node`;

CREATE TABLE `underseaobr_node` (
  `id` varchar(32) NOT NULL,
  `oid` int(11) DEFAULT NULL COMMENT '所属机构',
  `node_name` varchar(50) DEFAULT NULL COMMENT '节点名称',
  `lon` decimal(6,6) DEFAULT NULL,
  `lat` decimal(6,6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='海底观测网节点';

/*Data for the table `underseaobr_node` */

/*Table structure for table `work_duty_manage` */

DROP TABLE IF EXISTS `work_duty_manage`;

CREATE TABLE `work_duty_manage` (
  `id` varchar(32) NOT NULL,
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `del` tinyint(1) DEFAULT '0' COMMENT '被删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='观测月报';

/*Data for the table `work_duty_manage` */

/*Table structure for table `work_mail` */

DROP TABLE IF EXISTS `work_mail`;

CREATE TABLE `work_mail` (
  `id` varchar(32) NOT NULL,
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `del` tinyint(1) DEFAULT '0' COMMENT '被删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='观测月报';

/*Data for the table `work_mail` */

/*Table structure for table `work_month_observe` */

DROP TABLE IF EXISTS `work_month_observe`;

CREATE TABLE `work_month_observe` (
  `id` varchar(32) NOT NULL COMMENT '海洋站月报编号',
  `otype` varchar(50) DEFAULT NULL COMMENT '报送单位类型',
  `oid` varchar(50) DEFAULT NULL COMMENT '站标识',
  `OSEMRTime` varchar(50) DEFAULT NULL COMMENT '时间',
  `OSEMRPeriod` varchar(10) DEFAULT NULL COMMENT '期数',
  `OSSNType` varchar(20) DEFAULT NULL,
  `OSSNID` varchar(50) DEFAULT NULL,
  `SNEMRName` varchar(50) DEFAULT NULL COMMENT '站名',
  `OSEMRLeader` varchar(50) DEFAULT NULL COMMENT '直接保存名字',
  `OSEMRFiller` varchar(50) DEFAULT NULL COMMENT '填报人直接保存名字',
  `OSEMRChecker` varchar(50) DEFAULT NULL COMMENT '校对人直接保存名字',
  `OSEMRSumExpectData` int(11) DEFAULT NULL COMMENT '设备应测数据量合计',
  `OSEMRSumRealData` int(11) DEFAULT NULL COMMENT '设备实测数据量合计',
  `OSEMRExpectReport` int(11) DEFAULT NULL COMMENT '实时数据通讯-应发报数',
  `OSEMRRealReport` int(11) DEFAULT NULL COMMENT '实时数据通讯-实发报数',
  `OSEMRReportPct` double(53,0) DEFAULT NULL COMMENT '实时数据通讯-实发百分比',
  `OSEMRReportInstruction` text COMMENT '实时数据通讯-说明',
  `OSEMRExpectFile` int(11) DEFAULT NULL COMMENT '月资料文件-应报文件',
  `OSEMRRealFile` int(11) DEFAULT NULL COMMENT '月资料文件-实报文件',
  `OSEMRFilePct` double(53,0) DEFAULT NULL COMMENT '月资料文件-实发百分比',
  `OSEMRFileInstruction` text COMMENT '月资料文件-说明',
  `OSEMRFillDate` varchar(50) DEFAULT NULL COMMENT '填报日期',
  `OSEMRCreatePeople` int(11) DEFAULT NULL COMMENT '填写人编号（用户编号）',
  `OSEMRCreateTime` datetime DEFAULT NULL COMMENT '填写时间',
  `OSEMRReportFile` varchar(32) DEFAULT NULL COMMENT '月报文件',
  `create_user_id` bigint(20) DEFAULT NULL,
  `update_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(255) DEFAULT NULL,
  `del` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='海洋站设备月报情况';

/*Data for the table `work_month_observe` */

/*Table structure for table `work_month_observe_equip` */

DROP TABLE IF EXISTS `work_month_observe_equip`;

CREATE TABLE `work_month_observe_equip` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '海洋站月报告表仪器情况编号',
  `observe_id` bigint(20) DEFAULT NULL COMMENT '海洋站月报告表ID',
  `EEMRProName` varchar(50) DEFAULT NULL,
  `EEMRName` varchar(50) DEFAULT NULL COMMENT '仪器名称',
  `OSEEMRWorkStatus` varchar(50) DEFAULT NULL COMMENT '仪器工作情况',
  `OSEEMRExpectData` int(11) DEFAULT NULL COMMENT '应测数据量',
  `OSEEMRRealData` int(11) DEFAULT NULL COMMENT '实测数据量',
  `OSEEMRLackMeasureTime` varchar(500) DEFAULT NULL COMMENT '缺测时段',
  `OSEEMRLackMeasureReason` varchar(1000) DEFAULT NULL COMMENT '缺测原因',
  `OSEEMRLackMeasureStrategy` varchar(1000) DEFAULT NULL COMMENT '缺测对策',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COMMENT='海洋站月报告表仪器情况';

/*Data for the table `work_month_observe_equip` */

/*Table structure for table `work_patrol` */

DROP TABLE IF EXISTS `work_patrol`;

CREATE TABLE `work_patrol` (
  `id` varchar(32) NOT NULL,
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `del` tinyint(1) DEFAULT '0' COMMENT '被删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='陆岸巡视';

/*Data for the table `work_patrol` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
