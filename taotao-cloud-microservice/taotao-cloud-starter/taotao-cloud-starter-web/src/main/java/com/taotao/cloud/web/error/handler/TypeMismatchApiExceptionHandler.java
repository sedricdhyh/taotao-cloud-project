package com.taotao.cloud.web.error.handler;

import com.taotao.cloud.web.error.ApiErrorResponse;
import com.taotao.cloud.web.error.ErrorHandlingProperties;
import com.taotao.cloud.web.error.mapper.ErrorCodeMapper;
import com.taotao.cloud.web.error.mapper.ErrorMessageMapper;
import com.taotao.cloud.web.error.mapper.HttpStatusMapper;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * TypeMismatchApiExceptionHandler
 *
 * @author shuigedeng
 * @version 2021.10
 * @since 2022-01-12 08:58:50
 */
public class TypeMismatchApiExceptionHandler extends AbstractApiExceptionHandler {

	public TypeMismatchApiExceptionHandler(ErrorHandlingProperties properties,
		HttpStatusMapper httpStatusMapper,
		ErrorCodeMapper errorCodeMapper,
		ErrorMessageMapper errorMessageMapper) {
		super(httpStatusMapper, errorCodeMapper, errorMessageMapper);
	}

	@Override
	public boolean canHandle(Throwable exception) {
		return exception instanceof TypeMismatchException;
	}

	@Override
	public ApiErrorResponse handle(Throwable exception) {
		ApiErrorResponse response = new ApiErrorResponse(
			getHttpStatus(exception, HttpStatus.BAD_REQUEST),
			getErrorCode(exception),
			getErrorMessage(exception));
		TypeMismatchException ex = (TypeMismatchException) exception;
		response.addErrorProperty("property", getPropertyName(ex));
		response.addErrorProperty("rejectedValue", ex.getValue());
		response.addErrorProperty("expectedType",
			ex.getRequiredType() != null ? ex.getRequiredType().getName() : null);
		return response;
	}

	private String getPropertyName(TypeMismatchException exception) {
		if (exception instanceof MethodArgumentTypeMismatchException) {
			return ((MethodArgumentTypeMismatchException) exception).getName();
		} else {
			return exception.getPropertyName();
		}
	}
}
