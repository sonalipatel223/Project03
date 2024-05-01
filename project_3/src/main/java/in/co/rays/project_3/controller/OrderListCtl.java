package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.OrderDTO;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.OrderModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(urlPatterns = { "/ctl/OrderListCtl" })
public class OrderListCtl extends BaseCtl{
	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
		OrderDTO dto=new OrderDTO();

		dto.setsName(request.getParameter("sName"));

		dto.setOrderTyp(request.getParameter("orderTyp"));
		dto.setQuantity(DataUtility.getInt(request.getParameter("type")));
		dto.setAddress(request.getParameter("address"));
		dto.setPrice(DataUtility.getInt(request.getParameter("amount")));

		
		populateBean(dto, request);
		return dto;
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("goget chaliiii.....");
		List list;
		List next;
		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
		OrderDTO dto = (OrderDTO) populateDTO(req);
		OrderModelInt model = ModelFactory.getInstance().getOrderModel();
		String ids=req.getParameter("id");
		if(ids !=null) {
			System.out.println("delete chali.....");
			dto.setId(DataUtility.getLong(ids));
			model.delete(dto);
			ServletUtility.setSuccessMessage("Data Deleted Successfully", req);
			ServletUtility.redirect(ORSView.ORDER_LIST_CTL, req, resp);
			return;
		}
		
		
		try {
			list = model.search(dto, pageNo, pageSize);

			ArrayList<OrderDTO> a = (ArrayList<OrderDTO>) list;

			next = model.search(dto, pageNo + 1, pageSize);
			ServletUtility.setList(list, req);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", req);
			}
			if (next == null || next.size() == 0) {
				req.setAttribute("nextListSize", 0);

			} else {
				req.setAttribute("nextListSize", next.size());
			}
			ServletUtility.setList(list, req);
			ServletUtility.setPageNo(pageNo, req);
			ServletUtility.setPageSize(pageSize, req);
			ServletUtility.forward(getView(), req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("rk dopodt chali....");
		List list = null;
		List next = null;
		int pageNo = DataUtility.getInt(req.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(req.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;
		OrderDTO dto = (OrderDTO) populateDTO(req);
		String op = DataUtility.getString(req.getParameter("operation"));
		System.out.println("op---->" + op);

		String[] ids = req.getParameterValues("ids");
		OrderModelInt model = ModelFactory.getInstance().getOrderModel();
		try {
			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				}

			}  else if (OP_RESET.equalsIgnoreCase(op)) {

				ServletUtility.redirect(ORSView.ORDER_LIST_CTL, req, resp);
				return;
			} 

			if (OP_BACK.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.ORDER_LIST_CTL, req, resp);
				return;
			}
 		    dto = (OrderDTO) populateDTO(req);
//			System.out.println("y yyyyyyyyyy" + dto.getRoleId());

			list = model.search(dto, pageNo, pageSize);

			ServletUtility.setDto(dto, req);
			next = model.search(dto, pageNo + 1, pageSize);

			ServletUtility.setList(list, req);
			ServletUtility.setList(list, req);
			if (list == null || list.size() == 0) {
				if (!OP_DELETE.equalsIgnoreCase(op)) {
					ServletUtility.setErrorMessage("No record found ", req);
				}
			}
			if (next == null || next.size() == 0) {
				req.setAttribute("nextListSize", 0);

			} else {
				req.setAttribute("nextListSize", next.size());
			}
			ServletUtility.setList(list, req);
			ServletUtility.setPageNo(pageNo, req);
			ServletUtility.setPageSize(pageSize, req);
			ServletUtility.forward(getView(), req, resp);

		} catch (Exception e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected String getView() {
		return ORSView.ORDER_LIST_VIEW;
	}
}



