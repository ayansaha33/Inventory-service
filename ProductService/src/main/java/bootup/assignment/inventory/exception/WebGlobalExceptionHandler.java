package bootup.assignment.inventory.exception;



import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import bootup.assignment.inventory.constants.WebApplicationConstants;


/**
 * Global Exception Handler
 * This is used to handle exception scenarios which are not handled by individual controllers.
 */
@ControllerAdvice
public class WebGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger EXCEPTION_LOGGER = LoggerFactory.getLogger(WebGlobalExceptionHandler.class);


    /**
     * It does application exception handling for WebApplicationException
     *
     * @param inRequest the http servlet request
     * @param inException the actual exception occurred
     * @return ResponseWrapperBean the error message build based on exception
     */
    @ExceptionHandler(WebApplicationException.class)
    public ResponseEntity<Object> handleWebApplicationException(final HttpServletRequest inRequest, final WebApplicationException inException) {
        EXCEPTION_LOGGER.error("Below application exception occured while processing request: " + inRequest.getRequestURL());
        EXCEPTION_LOGGER.error("Application Exception : ", inException);
        
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        
        if (StringUtils.isNotEmpty(inException.getMessage())) {
        	body.put("message", inException.getMessage());
        }else {
        	body.put("message", WebApplicationConstants.PROP_ERROR_GLOBAL_EXCEPTION);
        }
        return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
    }
    
    
    /**
     * It does application exception handling for WebApplicationException
     *
     * @param inRequest the http servlet request
     * @param inException the actual exception occurred
     * @return ResponseWrapperBean the error message build based on exception
     */
    @ExceptionHandler(BlockedProductException.class)
    public ResponseEntity<Object> handleBlockedProductException(final HttpServletRequest inRequest, final BlockedProductException inException) {
        EXCEPTION_LOGGER.error("Below application exception occured while processing request: " + inRequest.getRequestURL());
        EXCEPTION_LOGGER.error("Application Exception : ", inException);
        
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        
        if (StringUtils.isNotEmpty(inException.getMessage())) {
        	body.put("message", inException.getMessage());
        }else {
        	body.put("message", WebApplicationConstants.PROP_ERROR_GLOBAL_EXCEPTION);
        }
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    /**
     * It does common exception handling for all other Exception
     *
     * @param inRequest the http servlet request
     * @param inException the actual exception occurred
     * @return ResponseWrapperBean the error message build based on exception
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(final HttpServletRequest inRequest, final Exception inException) {
        EXCEPTION_LOGGER.error("Below exception occured while processing request: " + inRequest.getRequestURL());
        EXCEPTION_LOGGER.error("Exception : ", inException);
        
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", WebApplicationConstants.PROP_ERROR_GLOBAL_EXCEPTION+":: "+ inException.getMessage());
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

}
