package cn.segema.cloud.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.segema.cloud.system.domain.Organization;
import cn.segema.cloud.system.vo.OrganizationPersonalVO;
import cn.segema.cloud.system.vo.OrganizationTreeVO;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, String> {
	
	 @Query("select new cn.segema.cloud.system.vo.OrganizationPersonalVO(o.organizationId,o.organizationName,o.organizationCode,p.personalId,p.personalName,op.type)"
	 		+ " from Organization o,OrganizationPersonal op,Personal p where o.organizationName = ?1 and o.organizationId=op.organization and op.personal = p.personalId ") 
	 public List<OrganizationPersonalVO> findOrganizationPersonalByName(String organizationName); 
	 
	 @Query("SELECT o from Organization o  where o.organizationName = ?1 ") 
	 public List<Organization> findByOrganizationName(String organizationName); 
	 
	 @Query("SELECT max(o.organizationCode) as organizationCode from Organization o  where o.parentCode = ?1 ") 
	 public Integer findMaxOrganization(Integer parentOrganizationCode); 
	 
	 @Query("SELECT o from Organization o  where o.parentCode = ?1 ") 
	 public List<OrganizationTreeVO> findTreeList(Integer parentOrganizationCode); 
}
