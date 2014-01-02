/**
 * 
 */
package com.caller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caller.model.Call;
import com.caller.rest.domain.Response;
import com.caller.service.CallService;

/**
 * @author Simeon Kredatus
 *
 */
@Controller
@RequestMapping("/call")
public class CallController {
	
	@Autowired
	private CallService callService;
	
	@RequestMapping(value = "/{id}")
	public @ResponseBody Response getCallInformation(@PathVariable String id) {
		Long callId = Long.parseLong(id);
		Call call = callService.getCall(callId);
		
		return prepareCallResponse(call);
	}

	/**
	 * @param call
	 * @return
	 */
	private Response prepareCallResponse(Call call) {
		Response response = new Response();
		response.setDial(call.getToNumber());
		response.setSay(call.getMessageText());
		return response;
	}
}
