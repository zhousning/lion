package app.services;

import app.models.ImageAttachment;

public interface ImageAttachmentService extends BaseService<ImageAttachment> {
	public ImageAttachment findByUrl(String url);
}