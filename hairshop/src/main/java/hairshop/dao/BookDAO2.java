package hairshop.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import common.Connection_pool;
import hairshop.dto.ShopDTO;

public class BookDAO2 extends Connection_pool{

	public List<ShopDTO> read_shop() {//전체 매장 리스트 받아서 list에 저장
		ShopDTO dto = new ShopDTO();
		List<ShopDTO> lists = new Vector<>();
		String query = "select * from shop";
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				dto.setSclose(rs.getString("sclose"));
				dto.setSlocation(rs.getString("slocation"));
				dto.setSmno(rs.getString("smno"));
				dto.setSname(rs.getString("sname"));
				dto.setSno(rs.getString("sno"));
				dto.setSopen(rs.getString("sopen"));
				lists.add(dto);
			}//while end
		} catch (SQLException e) {
			System.out.println("BookDAO2.read_all 오류");
			e.printStackTrace();
		}
		
		
		return lists;
	}
	
	public List<ShopDTO> read_designer() {//전체 디자이너 리스트 받아서 list에 저장
		ShopDTO dto = new ShopDTO();
		List<ShopDTO> lists = new Vector<>();
		String query = "select * from shop";
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				dto.setSclose(rs.getString("sclose"));
				dto.setSlocation(rs.getString("slocation"));
				dto.setSmno(rs.getString("smno"));
				dto.setSname(rs.getString("sname"));
				dto.setSno(rs.getString("sno"));
				dto.setSopen(rs.getString("sopen"));
				lists.add(dto);
			}//while end
		} catch (SQLException e) {
			System.out.println("BookDAO2.read_all 오류");
			e.printStackTrace();
		}
		
		
		return lists;
	}
	
	
}//class end
