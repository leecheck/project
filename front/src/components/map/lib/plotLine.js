import TWEEN, { Tween } from '@tweenjs/tween.js'

/**
 * 地图情节故事线
 * （一个地图视角变换队列 使用递归和promise保证队列顺序及最终完成事件）
 */
export default class PlotLine {

    /**
     * 构造方法 开启帧动画
     */
    constructor() {

        let that = this;
        let ainimation = function() {
            that.yourAnimation();
            TWEEN.update();
            requestAnimationFrame(ainimation);
        }

        this.animate = requestAnimationFrame(ainimation);

    }

    /**
     * 数据格式定义 { plotClips }
     * name属性要填 且唯一（队列最后一个name必须唯一）
     */
    testMtkClips() {
        return [{
            name: "clip1",
            route: {
                zoom: 6,
                lon: 120,
                lat: 36,
                pitch: 0,
                bearing: 0
            },
            wait:5000,
            during: 5000
        }, {
            name: "clip2",
            route: {
                zoom: 8,
                lon: 121.8,
                lat: 36.7,
                pitch: 30,
                bearing: 0
            },
            during: 5000
        }, {
            name: "clip3",
            route: {
                zoom: 11,
                lon: 122.1,
                lat: 37.5,
                pitch: 30,
                bearing: 0
            },
            during: 5000
        }, {
            name: "clip4",
            route: {
                zoom: 14,
                lon: 122.07,
                lat: 37.59,
                pitch: 30,
                bearing: 0
            },
            during: 5000
        }, {
            name: "clip5",
            route: {
                zoom: 14,
                lon: 122.07,
                lat: 37.59,
                pitch: 30,
                bearing: 360
            },
            during: 5000
        }, {
            name: "clip6",
            route: {
                zoom: 6,
                lon: 120,
                lat: 36,
                pitch: 0,
                bearing: 0
            },
            during: 5000
        }]
    }


    /**
     * 销毁
     */
    distroy() {
        cancelAnimationFrame(this.animate);
    }

    /**
     * 如果需要做一些自定义处理
     */
    yourAnimation() {
        //do some thing

    }


    /**
     * 视角飞行控制队列 
     * @param {*} map mtkmap
     * @param {*} param0 
     * plotClips 规定的队列数据结构
     * onStart 单次飞行开始事件（clipname，tween）
     * onUpdate（clipname，tween）
     * onComplete（clipname，tween）
     */
    mtkFlyPatrol( map , { plotClips, onStart, onUpdate, onComplete, onWait}) {

        let ez = {
            'Cubic.InOut': TWEEN.Easing.Cubic.InOut,
            'Cubic.In': TWEEN.Easing.Cubic.In,
            'Cubic.Out': TWEEN.Easing.Cubic.Out,
        }

        let mapView = map.getView();

        let current = {
            name: "clip init",
            route: {
                zoom: mapView.zoom,
                lon: mapView.center[0],
                lat: mapView.center[1],
                pitch: mapView.pitch,
                bearing: mapView.bearing
            },
            during: 5000
        }

        plotClips.unshift(current);

        let endClipName = plotClips[plotClips.length - 1].name;

        return new Promise((resole, reject) => {

            let animationPatrol = (clips) => {
                if (clips instanceof Array && clips.length > 1) {
                    let clipLength = clips.length;
                    let clip = clips[0]
                    if (clipLength > 1) {
                        let next = clips[1];
                        let tweenClip = new TWEEN.Tween(clip.route);
                        let during = next['during'] ? next['during'] : 4000;
                        let easing = next['easing'] ? next['easing'] : 'Cubic.InOut';
                        tweenClip.to(next.route, during).easing(ez[easing])
                            .onStart((up) => {
                                onStart(clip.name, up)
                            })
                            .onUpdate(function(up) {
                                onUpdate(clip.name, up)
                                map.setView({
                                    zoom: up.zoom,
                                    center: [up.lon, up.lat],
                                    pitch: up.pitch,
                                    bearing: up.bearing,
                                })
                            }).onComplete(function(up) {
                                clips.shift();
                                onComplete(clip.name, up);
                                if (next.name == endClipName) {
                                    resole();
                                } else {
                                    if(clip.wait != undefined){
                                        setTimeout(function(){
                                            animationPatrol(clips)
                                        },clip.wait)
                                        if(onWait && typeof onWait == 'function'){
                                            onWait(clip.name,next.name)
                                        }
                                    }else{
                                        animationPatrol(clips);
                                    }
                                }
                            }).start();
                    }
                } else {
                    return;
                }
            }

            animationPatrol(plotClips);
        })

    }


    /**
     * 基础clip控制队列 
     * @param {*} param0 
     * plotClips 规定的队列数据结构
     * onStart 单次开始事件（clipname，tween）
     * onUpdate（clipname，tween）
     * onComplete（clipname，tween）
     */
    plotControl({ plotClips, onStart, onUpdate, onComplete }){

        let ez = {
            'Cubic.InOut': TWEEN.Easing.Cubic.InOut,
            'Cubic.In': TWEEN.Easing.Cubic.In,
            'Cubic.Out': TWEEN.Easing.Cubic.Out,
        }

        let endClipName = plotClips[plotClips.length - 1].name;

        return new Promise((resole, reject) => {

            let animationPatrol = (clips) => {
                if (clips instanceof Array && clips.length > 1) {
                    let clipLength = clips.length;
                    let clip = clips[0]
                    if (clipLength > 1) {
                        let next = clips[1];
                        let tweenClip = new TWEEN.Tween(clip.route);
                        let during = next['during'] ? next['during'] : 4000;
                        let easing = next['easing'] ? next['easing'] : 'Cubic.InOut';
                        tweenClip.to(next.route, during).easing(ez[easing])
                            .onStart((up) => {
                                onStart(clip.name, up)
                            })
                            .onUpdate(function(up) {
                                onUpdate(clip.name, up)
                            }).onComplete(function(up) {
                                clips.shift();
                                onComplete(clip.name, up);
                                if (next.name == endClipName) {
                                    resole();
                                } else {
                                    animationPatrol(clips);
                                }
                            }).start();
                    }
                } else {
                    return;
                }
            }

            animationPatrol(plotClips);
        })
    }
}