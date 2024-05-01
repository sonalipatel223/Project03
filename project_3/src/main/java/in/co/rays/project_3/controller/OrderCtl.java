package in.co.rays.project_3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.OrderDTO;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.OrderModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(urlPatterns = { "/ctl/OrderCtl" })
public class OrderCtl extends BaseCtl {

	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		System.out.println("Started validate");
		if (DataValidator.isNull(request.getParameter("sName"))) {
			request.setAttribute("sName", PropertyReader.getValue("error.require", "sName"));
			pass = false;
			System.out.println("khjghjkhjg");
		} else if (!DataValidator.isName(request.getParameter("sName"))) {
			request.setAttribute("sName", "Name must contain alphabets only");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("order"))) {
			request.setAttribute("order", PropertyReader.getValue("error.require", "order"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("order"))) {
			request.setAttribute("order", "account must contain number only");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("quantity"))) {
			request.setAttribute("quantity", PropertyReader.getValue("error.require", "quantity"));
			pass = false;
		} else if (!DataValidator.isInteger(request.getParameter("quantity"))) {
			request.setAttribute("quantity", "amount must contain number only");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address", PropertyReader.getValue("error.require", "address"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("address"))) {
			request.setAttribute("address", "amount must contain number only");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("price"))) {
			request.setAttribute("price", PropertyReader.getValue("error.require", "price"));
			pass = false;
		} else if (!DataValidator.isInteger(request.getParameter("price"))) {
			request.setAttribute("price", "amount must contain number only");
			pass = false;
		}

		return pass;
	}

	protected BaseDTO populateDTO(HttpServletRequest request) {
		OrderDTO dto = new OrderDTO();
		dto.setsName(request.getParameter("sName"));

		dto.setOrderTyp(request.getParameter("order"));
		dto.setQuantity(DataUtility.getInt(request.getParameter("quantity")));
		dto.setAddress(request.getParameter("address"));
		dto.setPrice(DataUtility.getInt(request.getParameter("price")));

		populateBean(dto, request);
		return dto;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long id = DataUtility.getLong(req.getParameter("id"));
		System.out.println("order ctl ki doget chaliii....!!!!!!");
		OrderModelInt model = ModelFactory.getInstance().getOrderModel();
		if (id > 0) {
			OrderDTO dto;
			dto = model.findByPk(id);
			ServletUtility.setDto(dto, req);

		}
		ServletUtility.forward(getView(), req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("dopost chali......");
		String op = req.getParameter("operation");
		long id = DataUtility.getLong(req.getParameter("id"));
		System.out.println(op);
		OrderModelInt model = ModelFactory.getInstance().getOrderModel();
		// OrderDTO dto=new OrderDTO();
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			OrderDTO dto = (OrderDTO) populateDTO(req);
			if (id > 0) {
				dto.setId(id);
				model.update(dto);
				ServletUtility.setSuccessMessage("Record Successfully Updated", req);
				ServletUtility.setDto(dto, req);
			} else {
				model.add(dto);
				ServletUtility.setSuccessMessage("Record Successfully Saved", req);
				ServletUtility.setDto(dto, req);
			}

		} else if (OP_RESET.equalsIgnoreCase(op)) {
			System.out.println("reset challi....");
			ServletUtility.redirect(ORSView.ORDER_CTL, req, resp);
			return;
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.ORDER_LIST_CTL, req, resp);
			System.out.println("run on cancel !!!!!!!!!rk");
			return;

		}
		ServletUtility.forward(getView(), req, resp);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.ORDER_VIEW;
	}

}
