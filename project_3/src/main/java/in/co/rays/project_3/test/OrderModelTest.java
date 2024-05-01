package in.co.rays.project_3.test;

import java.util.Iterator;
import java.util.List;

import in.co.rays.project_3.dto.OrderDTO;
import in.co.rays.project_3.model.OrderModelHibImp;

public class OrderModelTest {

	public static void main(String[] args) {
		//testAdd();
		//testUpdate();
		 // testDelete();
		//testFindByPk();
		testSearch();

	}

	private static void testSearch() {
		OrderDTO dto=new OrderDTO();
		OrderModelHibImp model=new OrderModelHibImp();
		List list=model.search(dto, 0, 0);
		Iterator<OrderDTO>it=list.iterator();
		while(it.hasNext()) {
			dto=(OrderDTO)it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getsName());
			System.out.println(dto.getOrderTyp());
			System.out.println(dto.getQuantity());
			System.out.println(dto.getAddress());
			System.out.println(dto.getPrice());
			
		}
		
		
	}

	private static void testFindByPk() {
		OrderModelHibImp model=new OrderModelHibImp();
		OrderDTO dto=model.findByPk(2L);
		System.out.println(dto.getId());
		System.out.println(dto.getsName());
		System.out.println(dto.getOrderTyp());
		System.out.println(dto.getQuantity());
		System.out.println(dto.getAddress());
		System.out.println(dto.getPrice());
		
		
	}

	private static void testDelete() {
		OrderDTO dto=new OrderDTO();
		dto.setId(3L);
		OrderModelHibImp model=new OrderModelHibImp();
		model.delete(dto);
		
		
	}

	private static void testUpdate() {
		OrderDTO dto=new OrderDTO();
		dto.setId(2L);
		dto.setsName("sham");
		dto.setOrderTyp("cloth");
		dto.setQuantity(9);
		dto.setAddress("pune");
		dto.setPrice(3000);
		dto.setCreatedBy("abc");
		OrderModelHibImp model=new OrderModelHibImp();
		model.update(dto);
		
	}

	private static void testAdd() {
		OrderDTO dto=new OrderDTO();
		//dto.setId(1L);
		dto.setsName("rahul");
		dto.setOrderTyp("food");
		dto.setQuantity(5);
		dto.setAddress("indore");
		dto.setPrice(3400);
		OrderModelHibImp model=new OrderModelHibImp();
		model.add(dto);
		
	}

}
