package com.cognizant.cdc.controller

import com.cognizant.cdc.model.Attachment
import com.cognizant.cdc.service.AttachmentService
import com.cognizant.cdc.util.Utils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class AttachmentController {

    @Autowired
    AttachmentService attachmentService


    @RequestMapping(value="attachment/{id}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getAttachment(@PathVariable("id") String attachmentId) {

        println attachmentId

        if(attachmentId == null) {
            return new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
        }

        Attachment attachment = attachmentService.getAttachment(attachmentId)

        if(attachment == null) {
            return new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
        }


        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(Utils.getContentType(Utils.getSuffix(attachment.originalName)))
        headers.setContentDispositionFormData("inline", attachment.originalName);

        new ResponseEntity<byte[]>(attachment?.content, headers, HttpStatus.OK)
    }

}
