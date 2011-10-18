package dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class GenericDAO<T> extends DAO{
	
	
	@SuppressWarnings("unchecked")
	public Class<T> domainClass = getDomainClass();

 
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Class getDomainClass() {
	 	if (domainClass == null) {
			ParameterizedType thisType = (ParameterizedType) getClass()
 				.getGenericSuperclass();
			domainClass = (Class) thisType.getActualTypeArguments()[0];
	 	}
		return domainClass;
	}
	
	
	public void eliminar(T t) throws Exception {
		try {
			begin();
			getSession().delete(t);
			commit();
		} catch (Exception e) {
			rollback();
			throw e;
		}
	}
	
	
	public T crear(T t) throws Exception {
		try {
			begin();
			getSession().save(t);
			commit();
			return t;
		} catch (Exception e) {
			rollback();
			throw e;
		}
	}
	
	public void actualizar(T t) throws Exception {
		try {
			begin();
			getSession().update(t);
	 		commit();
	 	} catch (Exception e) {
	 		rollback();
			throw e;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<T> trearTodos() throws Exception {
		try {
			List<T> list = getSession().createQuery("from "+domainClass.getName()).list();

			return list;
		} catch (Exception e) {
			throw e;
		}
	}
	
	@SuppressWarnings("unchecked")
	public T trearPorId(Long id) {
		T returnValue = (T) getSession().load(domainClass, id);
		return returnValue;
	}
}
