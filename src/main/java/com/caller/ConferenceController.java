package com.caller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caller.model.ConferenceCall;
import com.caller.rest.domain.ConferenceResponse;
import com.caller.service.CallService;

@Controller
@RequestMapping("/conference")
public class ConferenceController {

	private CallService callService;
	
	@RequestMapping(value = "/{id}/{securityToken}")
	public @ResponseBody ConferenceResponse handleConferenceCall(@PathVariable String id) {
		Long conferenceCallId = Long.parseLong(id);
		ConferenceCall conferenceCall = callService.getConferenceCall(conferenceCallId);
		return new ConferenceResponse(conferenceCall.getConferenceRoom());
	}
}
