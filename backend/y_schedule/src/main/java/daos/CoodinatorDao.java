package daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import beans.EmployeeAvailabilityBean;
import util.HibernateUtil;

public class CoodinatorDao {
	public List<EmployeeAvailabilityBean> getPendingRequests() {
		Session session = HibernateUtil.getSession();
		Query query = null;
		String hql = "FROM EmployeeAvailabilityBean WHERE active = 0";
		query = session.createQuery(hql);
		
		List<EmployeeAvailabilityBean> list = query.list();
		
		session.close();
		
		return list;
	}
}
