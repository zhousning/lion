package app.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.daos.ImageAttachmentDao;
import app.models.ImageAttachment;
import app.services.ImageAttachmentService;

@Service
public class ImageAttachmentServiceImpl extends BaseServiceImpl<ImageAttachment> implements ImageAttachmentService {
	@Autowired
	ImageAttachmentDao imageAttachmentDao;

	@Override
	public ImageAttachment findByUrl(String url) {
		return imageAttachmentDao.findByUrl(url);
	}

}