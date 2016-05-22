package net.greenbeansit.jobtracker.server.data.activityReportTemplate;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

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
