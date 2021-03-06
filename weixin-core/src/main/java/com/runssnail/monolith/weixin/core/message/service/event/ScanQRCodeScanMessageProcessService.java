package com.runssnail.monolith.weixin.core.message.service.event;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.runssnail.monolith.common.result.Result;
import com.runssnail.monolith.weixin.core.message.domain.EnumEventType;
import com.runssnail.monolith.weixin.core.message.domain.Message;
import com.runssnail.monolith.weixin.core.message.domain.event.ScanQRCodeEventMessage;

/**
 * 扫描带参数二维码事件处理服务，用户已关注
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
@Service("scanQRCodeScanMessageProcessService")
@Order(21)
public class ScanQRCodeScanMessageProcessService extends AbstractEventMessageProcessService<ScanQRCodeEventMessage> {

    @Override
    public boolean supports(Message msg) {

        if (!super.supports(msg)) {
            return false;
        }

        if (!(msg instanceof ScanQRCodeEventMessage)) {
            return false;
        }

        ScanQRCodeEventMessage scan = (ScanQRCodeEventMessage) msg;

        return EnumEventType.isScan(scan.getEventType());
    }

    @Override
    protected void doProcess(ScanQRCodeEventMessage msg, Result<String> result) {

        if (log.isInfoEnabled()) {
            log.info("current message will be discarded(ScanQRCodeScanMessageProcessService). msg=" + msg);
        }

    }

}
