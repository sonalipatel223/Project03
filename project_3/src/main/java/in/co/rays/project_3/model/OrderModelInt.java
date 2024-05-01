package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.OrderDTO;

public interface OrderModelInt {
	public long add(OrderDTO dto);
	public void update(OrderDTO dto);
	public void delete(OrderDTO dto);
	public OrderDTO findByPk(long pk);
	public List search(OrderDTO dto,int pageNo,int pageSize);
	

}
