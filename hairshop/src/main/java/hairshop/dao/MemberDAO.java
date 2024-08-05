package hairshop.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import common.Connection_pool;
import hairshop.dto.MemberDTO;
import hairshop.dto.ShopDTO;

public class MemberDAO extends Connection_pool {

	public MemberDTO login(String id, String pw) {
	      MemberDTO memberDTO = null;
	      String sql = "SELECT * FROM member WHERE mid=? AND mpw=?";
	      try {
	         pst = con.prepareStatement(sql);
	         pst.setString(1, id);
	         pst.setString(2, pw);
	         rs = pst.executeQuery();
	         if (rs.next()) {
	            memberDTO = new MemberDTO();
	            memberDTO.setMno(rs.getInt("MNO"));
	            memberDTO.setMid(rs.getString("MID"));
	            memberDTO.setMpw(rs.getString("MPW"));
	            memberDTO.setMname(rs.getString("MNAME"));
	            memberDTO.setMphone(rs.getString("MPHONE"));
	            memberDTO.setMssno(rs.getString("MSSNO"));
	            memberDTO.setMgrade(rs.getInt("MGRADE"));
	         }
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }

	      return memberDTO;
	   }

	   public int join(MemberDTO memberDTO) {
	      String sql = "INSERT INTO MEMBER(MNO, MID, MPW, MNAME, MPHONE, MSSNO, MGRADE)"
	            + "VALUES(MNO_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?)";
	      int result = 0;

	      try {
	         PreparedStatement pst = con.prepareStatement(sql);
	         pst.setString(1, memberDTO.getMid());
	         pst.setString(2, memberDTO.getMpw());
	         pst.setString(3, memberDTO.getMname());
	         pst.setString(4, memberDTO.getMphone());
	         pst.setString(5, memberDTO.getMssno());
	         pst.setInt(6, memberDTO.getMgrade());
	         result = pst.executeUpdate();

	         if (result > 0) {
	            System.out.println("회원가입 성공");
	         } else {
	            System.out.println("회원가입 실패");
	         }

	      } catch (SQLException e) {
	         System.out.println("MemberDAO.join() 오류발생");
	         e.printStackTrace();
	      }

	      return result;
	   }
	   
	   public ShopDTO get_shopDTO(MemberDTO memberDTO) {
		   ShopDTO sdto = new ShopDTO();
		   String query = "select * from shop where smno=?";
		   try {
			pst=con.prepareStatement(query);
			   pst.setInt(1,memberDTO.getMno());
			   rs=pst.executeQuery();
			   if(rs.next()) {
				   sdto.setSname(rs.getString("sname"));
				   sdto.setSmno(rs.getString("smno"));
			   }
		} catch (SQLException e) {
			System.out.println("MemberDAO.get_shopDTO 오류");
			e.printStackTrace();
		}
		   return sdto;
	   }//method end
	
}//class end
