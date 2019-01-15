package app.daos.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import app.daos.ImageAttachmentDao;
import app.models.ImageAttachment;
import app.models.User;

@Repository
public class ImageAttachmentDaoImpl extends BaseDaoImpl<ImageAttachment> implements ImageAttachmentDao{

	@Override
	public ImageAttachment findByUrl(String url) {
		String hql = "from ImageAttachment i where i.url = :url";
		List<ImageAttachment> images = (List<ImageAttachment>) hibernateTemplate.findByNamedParam(hql, "url", url);
		ImageAttachment imageAttachment = null;
		if (images.size() != 0) {
			imageAttachment = images.get(0);
		}
		return imageAttachment;
	}

}