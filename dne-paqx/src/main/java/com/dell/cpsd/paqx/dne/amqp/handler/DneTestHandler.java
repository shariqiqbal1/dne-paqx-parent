package com.dell.cpsd.paqx.dne.amqp.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import com.dell.cpsd.DiscoveredNode;
import com.dell.cpsd.NodesListed;
import com.dell.cpsd.common.rabbitmq.consumer.error.ErrorTransformer;
import com.dell.cpsd.common.rabbitmq.consumer.handler.AsyncAcknowledgement;
import com.dell.cpsd.common.rabbitmq.consumer.handler.DefaultMessageHandler;
import com.dell.cpsd.common.rabbitmq.validators.DefaultMessageValidator;
import com.dell.cpsd.contract.extension.amqp.message.HasMessageProperties;

public class DneTestHandler extends DefaultMessageHandler<NodesListed>
implements  AsyncAcknowledgement<List<DiscoveredNode>>
{

    private Map<String, CompletableFuture<List<DiscoveredNode>>> asyncRequests = new HashMap<>();
    
    public DneTestHandler(String responseExchange, ErrorTransformer<HasMessageProperties<?>> errorTransformer)
    {
        super(NodesListed.class, new DefaultMessageValidator<>(), responseExchange, errorTransformer);
    }

    @Override
    public CompletableFuture<List<DiscoveredNode>> register(String correlationId)
    {
        CompletableFuture<List<DiscoveredNode>> completableFuture = new CompletableFuture<>();
        completableFuture.whenComplete((systemRest, throwable) -> asyncRequests.remove(correlationId));
        asyncRequests.put(correlationId, completableFuture);
        return completableFuture;
    }

    @Override
    protected void executeOperation(NodesListed nodesListedMessage) throws Exception
    {
        final String correlationId = nodesListedMessage.getMessageProperties().getCorrelationId();

        List<DiscoveredNode> discoveredNodes= nodesListedMessage.getDiscoveredNodes();

        final CompletableFuture<List<DiscoveredNode>> completableFuture = asyncRequests.get(correlationId);


        // TODO: write to data service
        if (completableFuture != null)
        {
            final boolean complete = completableFuture.complete(discoveredNodes);
            asyncRequests.remove(correlationId);
        }
    }
    
    
    
    

}
