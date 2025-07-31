package repository;

import java.util.List;

public interface Repository<T> {
	public int insert(T enitiy);
	public List<T> list();
	public int getCount();
	public T search(String id);
	public int  update(T entity);
	public int  delete(String id);
		
}
