package com.dell.cpsd.paqx.dne.repository;

import com.dell.cpsd.paqx.dne.domain.ComponentDetails;
import com.dell.cpsd.paqx.dne.repository.ComponentPersister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;

@Repository
abstract class AComponentPersister implements ComponentPersister
{
    private static final Logger LOG = LoggerFactory.getLogger(AComponentPersister.class);
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public boolean handleSave(final List<ComponentDetails> componentEndpointDetailsList)
    {
        LOG.info(saveMessage());

        if (componentEndpointDetailsList.isEmpty())
        {
            LOG.error("No Components Found");
            return false;
        }

        try
        {
            componentEndpointDetailsList.stream().filter(Objects::nonNull).forEach(componentDetails -> {
                String componentUuid = componentDetails.getComponentUuid();

                try
                {
                    TypedQuery<ComponentDetails> componentDetailsTypedQuery = entityManager
                            .createQuery("select c from ComponentDetails as c where c.componentUuid =:componentUuid",
                                    ComponentDetails.class);
                    componentDetailsTypedQuery.setParameter("componentUuid", componentUuid);

                    final List<ComponentDetails> existingComponentList = componentDetailsTypedQuery.getResultList();
                    if (existingComponentList != null && !existingComponentList.isEmpty())
                    {
                        entityManager.merge(existingComponentList.get(0));
                        entityManager.flush();
                    }
                    else
                    {
                        entityManager.persist(componentDetails);
                    }
                }
                catch (Exception e)
                {
                    LOG.error(exception1Message(), e);
                }
            });

            return true;
        }
        catch (Exception e)
        {
            LOG.error(exception2Message(), e);
            return false;
        }
    }

    abstract String exception1Message();

    abstract String exception2Message();

    abstract String saveMessage();
}
