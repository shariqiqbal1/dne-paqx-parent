
package com.dell.cpsd.storage.capabilities.api;

import java.io.Serializable;
import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "link"
})
public class CatalogService implements Serializable
{

    @JsonProperty("id")
    private String id;
    /**
     * 
     * Corresponds to the "link" property.
     * 
     */
    @JsonProperty("link")
    @Valid
    private Link link;
    private final static long serialVersionUID = 6589349890860141070L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CatalogService() {
    }

    /**
     * 
     * @param link
     * @param id
     */
    public CatalogService(String id, Link link) {
        super();
        this.id = id;
        this.link = link;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public CatalogService withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * 
     * Corresponds to the "link" property.
     * 
     */
    @JsonProperty("link")
    public Link getLink() {
        return link;
    }

    /**
     * 
     * Corresponds to the "link" property.
     * 
     */
    @JsonProperty("link")
    public void setLink(Link link) {
        this.link = link;
    }

    public CatalogService withLink(Link link) {
        this.link = link;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(link).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CatalogService) == false) {
            return false;
        }
        CatalogService rhs = ((CatalogService) other);
        return new EqualsBuilder().append(id, rhs.id).append(link, rhs.link).isEquals();
    }

}
