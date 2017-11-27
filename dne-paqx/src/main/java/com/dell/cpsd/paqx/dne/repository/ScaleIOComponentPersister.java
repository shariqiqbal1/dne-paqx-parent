package com.dell.cpsd.paqx.dne.repository;

import org.springframework.stereotype.Repository;

@Repository
public class ScaleIOComponentPersister extends AComponentPersister
{

    public ScaleIOComponentPersister()
    {
        System.out.println("SC:EntityManager is: " + entityManager);
    }
    @Override
    String saveMessage()
    {
        return "Persisting ScaleIO Component, Endpoint and Credential UUID";
    }

    @Override
    public String exception1Message()
    {
        return "Exception occurred while persisting scaleio component details [{}]";
    }

    @Override
    public String exception2Message()
    {
        return " Exception occurred while persisting scaleio data";
    }
}
