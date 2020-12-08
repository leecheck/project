import * as maptalks from 'maptalks';

export default class Popup {

    /**
     * 计算偏移 此时width310 height300 dx=310/2 dy = 300/2-（50+30）
     * @param {*} map
     */
    constructor(map, { initWidth,initHeight,lineColor, bgColor, bgImg }) {
        let that = this;
        this.map = map;
        this.popData = {}

        this.initWidth = initWidth || 330;
        this.initHeight = initHeight || 300;
        this.guideLine1len = 50;
        this.guideLine1top = 50;
        this.guideLine2top = 32;
        this.dx = this.initWidth / 2 - 5;
        this.dy = this.initHeight / 2 - (this.guideLine1top + (this.guideLine1len * Math.cos(Math.PI / 4)) / 2);

        this.lineColor = lineColor || '#00daf8';
        this.bgColor = bgColor || '#02003D';
        this.lineColor = lineColor || '#00daf8';
        this.bgImg = bgImg;
        this.initStaticDom(this.parent);
        this.popMarker = undefined;
        this.map.closePop = () => {
            that.popClose();
        }
    }

    initStaticDom() {
        let that = this;
        this.parent = document.createElement('div');
        this.parent.class = "pop-parent";
        this.parent.style.cssText = "position:relative;min-height: 300px;"
        this.parent.style.width = this.initWidth + 'px';
        this.parent.style.height = this.initHeight + 'px';
        this.parent.innerHTML = `
        <!-- 引导线 -->
        <div class='pop-guideline' style="position:absolute;left:0;top:0">
          <div style="position: absolute;left: 0;top: 50px;height: 1px;width: ${this.guideLine1len}px;transform: rotate(-45deg);border-top: 2px ${this.lineColor} solid;"></div>
          <div style="position:absolute;left:42px;top:${this.guideLine2top}px;height:1px;width:35px;border-top:2px #00daf8 solid"></div>
        </div>
        <!-- 内容框 -->
        <div class="pop-container" style="margin-left: 78px;background: ${this.bgColor};padding: 8px;">

        </div>
        <!-- 关 -->
        <div class="pop-close" style="position:absolute;right:14px;top:14px">✖</div>
        `;
        this.container = this.parent.querySelector('.pop-container')
        if (this.bgImg) {
            this.container.style.backgroundImage = 'url(' + this.bgImg + ')';
            this.container.style.backgroundRepeat = "no-repeat";
            this.container.style.backgroundPosition = "center";
            this.container.style.backgroundSize = "100% 100%";
            this.container.style.backgroundColor = "#ffffff00";
        }
        this.initYourDom()
        this.close = this.parent.querySelector('.pop-close');
        this.close.onclick = () => {
            this.popClose.call(that);
        }
    }

    initYourDom() {
        this.container.innerHTML = `
        <div class="pop-container-frame" style="position:relative;width:100%;height: 100%;overflow: hidden;box-shadow: rgba(3, 31, 60, 0.6) 2px 2px 15px;">
            <div class="popTitle" style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;margin:5px auto;height: 26px;font-size: 18px;line-height: 26px;color: white;background: linear-gradient(to right, #1677FF00, #1677FC, #1677FF00) ">title</div>
            <div class="pop-status" style="height:45px;width:100%">
                <div class="circle-text" style="float: left;line-height: 45px;width: 25%;height: 100%;">运行状态</div>
                <div style="float: left;line-height: 45px;width: 25%;height: 100%;" id="quipState"></div>
                <div class="circle-text" style="float: left;line-height: 45px;width: 25%;height: 100%;">传输状态</div>
                <div style="float: left;line-height: 45px;width: 25%;height: 100%;" id="dataState"></div>
            </div>
            <div class="popSpans" style="float: left;width: 100%;padding: 3px;">
            </div>
            <div class="popLabel" style="float: left;width: 100%;">
            </div>
            <div class="popLabelBtn" style="float: left;width: 100%;">
            </div>
            <div class="pop-btns" style="float: left;width: 96%;margin:0 2%">
            </div>
        </div>`

        this.popTitle = this.container.querySelector('.popTitle');
        this.popSpans = this.container.querySelector('.popSpans');
        this.popLabel = this.container.querySelector('.popLabel');
        this.popLabelBtn = this.container.querySelector('.popLabelBtn');
        this.popStatus = this.container.querySelector('.pop-status');
        this.quipState = this.container.querySelector('#quipState');
        this.dataState = this.container.querySelector('#dataState');
        this.btnContent = this.container.querySelector('.pop-btns');
    }

    popClose(evt) {
        this.popMarker ? this.popMarker.hide() : 0;
    }

    checkClose(type){
        if(type == this.popData.type){
            this.popClose()
        }
    }

    /**
     * 数据格式化为弹窗规定 popData
     * @param {*} conf
     * @param {*} props
     * @returns popData
     */
    popProps() {
        return {
            type:"浮标",
            title: '001',
            prop: {
                name: "name",
                id: "id"
            },
            spans: [{
                name: "风速",
                value: "5.12",
                unit: "m/s"
            }, {
                name: "风向",
                value: "西北",
                unit: ""
            }, {
                name: "叶绿素",
                value: "0.725",
                unit: "mg/L"
            }, {
                name: "温度",
                value: "19.2",
                unit: "°C"
            }],
            labels: [{
                name: "部署海域",
                value: "威海东北部，沿海布设"
            }, {
                name: "部署海域",
                value: "威海东北部，沿海"
            }, {
                name: "部署海域",
                value: "威海东北部"
            }],
            btns: [{
                type: "detail",
                name: "详情"
            }]
        }
    }

    popShow(lonlat, popData) {
        this.popData = popData;
        this.yourDomOpt(popData || this.popProps());
        //this.staticDomOpt();
        if (!this.popMarker) {
            this.popMarker = new maptalks.ui.UIMarker(lonlat, {
                draggable: false,
                content: this.parent,
                dx: this.dx,
                dy: this.dy,
                animation :"fade"
            }).addTo(this.map)
        } else {
            this.popMarker.setOptions({
                content: this.parent,
                dx: this.dx,
                dy: this.dy
            });
            this.popMarker.setCoordinates(new maptalks.Coordinate(lonlat));
        }
        if (!this.popMarker.isVisible()) {
            this.popMarker.show();
        }
    }

    staticDomOpt() {
        this.tempHeight = this.container.offsetHeight;
        this.dx = this.initWidth / 2;
        this.dy = this.tempHeight / 2 - (this.guideLine1top + (this.guideLine1len * Math.cos(Math.PI / 4)) / 2);
        //this.parent.style.height = this.tempHeight + 5 + 'px';
    }

    yourDomOpt(popData) {
        this.popTitle.innerHTML = popData.type && popData.type!= "" ? "⌈" + popData.type + "⌋" :  ""
        this.popTitle.innerHTML = this.popTitle.innerHTML + " " + popData.title
        this.createSpans(popData);
        this.createLabels(popData);
        this.createLabelBtns(popData);
        this.createStatus(popData)
        this.createBtns(popData);
    }

    createStatus(popData) {
        this.quipState.innerHTML = "";
        this.quipState.appendChild(this.createCircle(16, "优", '#fff', 36));
        this.dataState.innerHTML = "";
        this.dataState.appendChild(this.createCircle(16, "优", '#fff', 36));
        if (popData.status) {
            this.popStatus.style.display = "block";
        } else {
            this.popStatus.style.display = "none";
        }
    }

    createLabelBtns(popData) {
        if(!popData.labelBtns || !popData.labelBtns instanceof Array){
            popData.labelBtns = []
        }
        this.popLabelBtn.innerHTML = "";
        let html = "";
        for (let i = 0; i < popData.labelBtns.length; i++) {
            let lbs = popData.labelBtns[i];
            html += `<div class="bootstrap popup-label-line" ><div class="popup-btns-label">${lbs.label}:</div>`; 
            let data = JSON.stringify(lbs.prop).replace(/\"/g, "'");
            html += "<a style='float:right' class='popup-label-btn ' href='javascript:void(0);' onclick=\"popupClick('" + lbs
            .type + "'," + data + ",'" + lbs.name + "');\">" + lbs.name + "</a>"
            html += `</div>`
        }
        this.popLabelBtn.innerHTML = html;
    }

    createLabels(popData) {
        if(!popData.labels || !popData.labels instanceof Array){
            popData.labels = []
        }
        this.popLabel.innerHTML = "";
        let html = "";
        for (let i = 0; i < popData.labels.length; i++) {
            let label = popData.labels[i];
            if (i % 2 == 0) {
                html += `
                <div class="bootstrap popup-label-line" ><div class="popup-label">${label.name}:</div><div class="popup-data" >${label.value}</div></div>`
            } else {
                html += `
                <div class=" popup-label-line" ><div class="popup-label">${label.name}:</div><div class="popup-data" >${label.value}</div></div>`
            }
        }
        this.popLabel.innerHTML = html;
    }

    createSpans(popData) {
        if(!popData.spans || !popData.spans instanceof Array){
            popData.spans = []
        }
        this.popSpans.innerHTML = "";
        let html = "";
        popData.spans.forEach((span) => {
            html += `
            <div class="rela pop-span" style=" width: 47%;height: 50px;border-bottom: 0.5px solid #08e8ffdd;float: left;margin: 1% 1.5%;">
                <div class="abso abs-hrizon-center show-text">
                ${span.value || '-'}
                </div>
                <div style="bottom:0" class="abso abs-hrizon-center label-text">
                    ${span.name} ${span.unit}
                </div>
            </div>`
        })
        this.popSpans.innerHTML = html;
    }

    createBtns(popData) {
        if(!popData.btns || !popData.btns instanceof Array){
            popData.btns = []
        }
        this.btnContent.innerHTML = "";
        let htmlContent = "";
        popData.btns.forEach(function(btn) {
            let data = JSON.stringify(popData.prop).replace(/\"/g, "'");
            htmlContent += "<a style='float:right' class='popup-btn ' href='javascript:void(0);' onclick=\"popupClick('" + btn
                .type + "'," + data + ",'" + btn.name + "');\">" + btn.name + "</a>";
            
        });
        this.btnContent.innerHTML = htmlContent;
    }

    createCircle(fontSize, text, fillColor, size) {
        let SIZE = size || 256;
        var canvas = document.createElement('canvas');
        let radius = SIZE / 10;
        canvas.width = canvas.height = SIZE + radius * 2;
        var ctx = canvas.getContext('2d');
        var gradient = ctx.createLinearGradient(0, 0, SIZE, 0);
        // gradient.addColorStop("0", "#ffffff");
        gradient.addColorStop("0.0", "#1a9bfc");
        gradient.addColorStop("1.0", "#7049f0");
        // gradient.addColorStop("0.66", "white");
        // gradient.addColorStop("1.0", "red");

        ctx.strokeStyle = gradient;
        ctx.lineWidth = radius;
        ctx.arc(SIZE / 2 + radius, SIZE / 2 + radius, SIZE / 2, 0, Math.PI * 2);
        ctx.stroke();
        ctx.fillStyle = fillColor;
        ctx.font = `${fontSize}px Aria`;
        ctx.textAlign = 'center';
        ctx.textBaseline = 'middle';
        ctx.fillText(text, SIZE / 2 + radius, SIZE / 2 + radius);
        ctx.rect(0, 0, SIZE + radius, SIZE + radius);
        return canvas;
    }

}
