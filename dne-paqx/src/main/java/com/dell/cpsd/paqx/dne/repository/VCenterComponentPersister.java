package com.dell.cpsd.paqx.dne.repository;

import org.springframework.stereotype.Repository;

@Repository
public class VCenterComponentPersister extends AComponentPersister
{
    public VCenterComponentPersister()
    {
        System.out.println("VC:EntityManager is: " + entityManager);
    }
    @Override
    String saveMessage()
    {
        return "Persisting VCenter Component, Endpoint and Credential UUID";
    }

    @Override
    String exception1Message()
    {
        return "Exception occurred while persisting vcenter component details[{}]";
    }

    @Override
    String exception2Message()
    {
        return "Exception occurred while persisting vcenter data";
    }
}
