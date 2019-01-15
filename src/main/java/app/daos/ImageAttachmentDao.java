package app.daos;

import app.models.ImageAttachment;

public interface ImageAttachmentDao extends BaseDao<ImageAttachment> {

	ImageAttachment findByUrl(String url);

}