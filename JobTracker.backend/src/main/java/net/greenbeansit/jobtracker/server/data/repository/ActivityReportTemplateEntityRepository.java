package net.greenbeansit.jobtracker.server.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.greenbeansit.jobtracker.server.data.entity.ActivityReportTemplateEntity;

public interface ActivityReportTemplateEntityRepository extends CrudRepository<ActivityReportTemplateEntity, Integer>
{

	void delete(ActivityReportTemplateEntity deleted);

	List<ActivityReportTemplateEntity> findAll();

	ActivityReportTemplateEntity findById(Integer id);

	@SuppressWarnings("unchecked")
	ActivityReportTemplateEntity save(ActivityReportTemplateEntity persisted);

	List<ActivityReportTemplateEntity> findByAuthor(Integer author);
	
	ActivityReportTemplateEntity findByAuthorAndName(Integer author, String name);
}
