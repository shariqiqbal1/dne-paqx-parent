package com.dell.cpsd.paqx.dne.repository;

import com.dell.cpsd.paqx.dne.domain.ComponentDetails;

import java.util.List;

public interface ComponentPersister
{
    boolean handleSave(final List<ComponentDetails> componentEndpointDetailsList);
}