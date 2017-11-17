
package com.dell.cpsd.paqx.dne.amqp.handler;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.dell.cpsd.contract.extension.amqp.message.ErrorContainer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"code", "message"})
public class Error implements ErrorContainer
{

    /**
     * Error code
     * <p>
     * The error code provided by the service. (Required)
     * 
     */
    @JsonProperty("code")
    @JsonPropertyDescription("The error code provided by the service.")
    @NotNull
    private String code;
    /**
     * Error message
     * <p>
     * The error message provided by the service. (Required)
     * 
     */
    @JsonProperty("message")
    @JsonPropertyDescription("The error message provided by the service.")
    @NotNull
    private String message;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Error()
    {
    }

    /**
     * 
     * @param code
     * @param message
     */
    public Error(String code, String message)
    {
        super();
        this.code = code;
        this.message = message;
    }

    /**
     * Error code
     * <p>
     * The error code provided by the service. (Required)
     * 
     */
    @JsonProperty("code")
    public String getCode()
    {
        return code;
    }

    /**
     * Error code
     * <p>
     * The error code provided by the service. (Required)
     * 
     */
    @JsonProperty("code")
    public void setCode(String code)
    {
        this.code = code;
    }

    public Error withCode(String code)
    {
        this.code = code;
        return this;
    }

    /**
     * Error message
     * <p>
     * The error message provided by the service. (Required)
     * 
     */
    @JsonProperty("message")
    public String getMessage()
    {
        return message;
    }

    /**
     * Error message
     * <p>
     * The error message provided by the service. (Required)
     * 
     */
    @JsonProperty("message")
    public void setMessage(String message)
    {
        this.message = message;
    }

    public Error withMessage(String message)
    {
        this.message = message;
        return this;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(code).append(message).toHashCode();
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == this)
        {
            return true;
        }
        if ((other instanceof Error) == false)
        {
            return false;
        }
        Error rhs = ((Error) other);
        return new EqualsBuilder().append(code, rhs.code).append(message, rhs.message).isEquals();
    }

}
