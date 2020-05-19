package danjunxian;

import java.util.ArrayList;
import java.util.List;

import ty.fx.bean.TimeDataBaseBean;
import ty.fx.utils.InputFileUtils;

public class DanJunXian {

	
	private void test() {
		
		List<TimeDataBaseBean> baseDataList = new ArrayList<TimeDataBaseBean>();
		
		baseDataList = InputFileUtils.getInputData("data/new/" + currencyPair + interval + ".csv");
	}
	

}
