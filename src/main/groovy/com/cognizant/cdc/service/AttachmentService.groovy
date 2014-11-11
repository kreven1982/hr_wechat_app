package com.cognizant.cdc.service

import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.springframework.stereotype.Service
import com.cognizant.cdc.model.Attachment
import org.springframework.beans.factory.annotation.Autowired
import com.cognizant.cdc.repository.AttachmentRepository
import org.bson.types.ObjectId


@CompileStatic
@TypeChecked
@Service
class AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository

    public String newAttachment(String originalFileName, byte[] content) {

        final String attachmentId = new ObjectId().toString()

        Attachment attachment = new Attachment(
                id: attachmentId,
                originalName: originalFileName,
                createTime: System.currentTimeMillis(),
                content: content,
                size: content.size()
        )

        attachmentRepository.save(attachment)

        attachmentId
    }

    public Attachment getAttachment(String id) {
        attachmentRepository.get(id)
    }

}
