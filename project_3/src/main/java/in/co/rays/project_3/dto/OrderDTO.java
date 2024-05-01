package in.co.rays.project_3.dto;

public class OrderDTO extends BaseDTO{
	
	private String sName;
	private String orderTyp;
	private int quantity;
	private String address;
	private int price;
	
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getOrderTyp() {
		return orderTyp;
	}
	public void setOrderTyp(String orderTyp) {
		this.orderTyp = orderTyp;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return sName;
	}
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return sName;
	}
	

}
