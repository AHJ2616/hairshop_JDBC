package hairshop.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import common.Connection_pool;
import hairshop.dto.CutDTO;

public class CutDAO extends Connection_pool {

	
	//시술 추가
	public int insertHair(CutDTO dto) {
		int applyResult = 0;
		String query = "insert into cut (cno,csno,csname,ccutname,cprice,ccontents,ofile, sfile) "
				+ "values (cno_seq.nextval,?,?,?,?,?,?,?)";
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, dto.getCsno());
			pst.setString(2, dto.getCsname());
			pst.setString(3, dto.getCcutname());
			pst.setString(4, dto.getCprice());
			pst.setString(5, dto.getCcontents());
			pst.setString(6, dto.getOfile());
			pst.setString(7, dto.getSfile());

			applyResult = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return applyResult;
	}// end insertHair

	// 출력 메서드
	public List<CutDTO> myFileList() {
		List<CutDTO> fileList = new Vector<CutDTO>(); // 멀티스레드용

		try {
			String qurey = "select * from cut order by cno desc"; // 모든 데이터를 찾아옴(내림차순)

			pst = con.prepareStatement(qurey);
			rs = pst.executeQuery(); // 쿼리문 실행 후 결과를 표로 받음

			while (rs.next()) {

				CutDTO dto = new CutDTO(); // 빈 객체 생성

				dto.setCno(rs.getString("cno"));
				dto.setCsno(rs.getString("csno"));
				dto.setCsname(rs.getString("csname"));
				dto.setCcutname(rs.getString("ccutname"));
				dto.setCcontents(rs.getString("ccontents"));
				dto.setCprice(rs.getString("cprice"));
				dto.setOfile(rs.getString("ofile"));
				dto.setSfile(rs.getString("sfile"));

				fileList.add(dto); // 리스트에 객체 삽입
			}
		} catch (SQLException e) {
			System.out.println("MyfileDAO.myFileList() 메서드 오류");
			System.out.println("select 쿼리문을 확인하세요");
			e.printStackTrace();
		}

		return fileList; // 결론 테이블에 있는 모든 값이 리스트의 객체로 리턴한다
	}
	
	//관리자 번호로 매장번호 가져오기
	
	
}// class end
