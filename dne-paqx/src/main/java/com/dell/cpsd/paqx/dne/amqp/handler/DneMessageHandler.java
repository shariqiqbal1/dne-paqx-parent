package com.dell.cpsd.paqx.dne.amqp.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.dell.cpsd.DiscoveredNode;
import com.dell.cpsd.NodesListed;
import com.dell.cpsd.common.rabbitmq.client.SendMessageService;

@RabbitListener(queues="queue.dell.cpsd.dne.test.request")
public class DneMessageHandler
{

    @Autowired
    private SendMessageService sendMessageService;
    @RabbitHandler
    public void handleListNodesRequest(NodesListed nodesListed){
        DiscoveredNode discoveredNode = new DiscoveredNode();
        List<DiscoveredNode> discoveredNodes= new ArrayList<DiscoveredNode>();
        nodesListed.setDiscoveredNodes(discoveredNodes);
        sendMessageService.sendMessage("exchange.dell.cpsd.dne.test.response.exchange", nodesListed.getMessageProperties().getReplyTo(), "com.dell.cpsd.dne.test.response.routing.key{replyTo}", nodesListed);
    }
}
