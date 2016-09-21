package com.rlogman.guaranteedrate.sorttest;

import java.io.Serializable;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true, includeFieldNames = true)
public class ErrorDescriptor implements Serializable {
  private static final long serialVersionUID = -4126510300324852195L;

  private String errorProcessor;
  private String message;
  private String code;
  private String exceptionMessage;
  private int httpStatusCode;
  
  private Map<String, Object> errorAttributes;
}
