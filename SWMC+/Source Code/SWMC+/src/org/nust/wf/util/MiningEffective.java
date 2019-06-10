package org.nust.wf.util;

import java.util.ArrayList;

import org.nust.wf.basicstruct.Edge;


public class MiningEffective {
	
	public double precision;	//召回率
	public double recall;		//准确率
	public double f_Measure;	//综合评价指标
	
	
	public double getPrecision() {
		return precision;
	}

	public void setPrecision(double precision) {
		this.precision = precision;
	}

	public double getRecall() {
		return recall;
	}

	public void setRecall(double recall) {
		this.recall = recall;
	}

	public double getF_Measure() {
		return f_Measure;
	}

	public void setF_Measure(double f_Measure) {
		this.f_Measure = f_Measure;
	}


	public MiningEffective(ArrayList<Edge> sampleList,ArrayList<Edge> testList){
		int tp=0;	//检测出来正确的边
		for(int i = 0;i<testList.size();i++){
			for(int j=0;j<sampleList.size();j++){
				if(testList.get(i).equals(sampleList.get(j))){
					tp++;
				}
			}
			
		}
		precision = 1.0*tp/testList.size();
		recall = 1.0*tp/sampleList.size();
		f_Measure = 2*precision*recall/(precision+recall);
	}

	/*public int MiningEffective(ArrayList<Edge> sampleList,ArrayList<Edge> testList){
		
		int tp=0;	//检测出来正确的边
		
		//打印挖掘出来的边
		System.out.println("-------sample---------------------"+"SIZE:"+sampleList.size());
		for(int i=0;i<sampleList.size();i++){
			System.out.println(sampleList.get(i).getSource().getName()+"--->"+sampleList.get(i).getTarget().getName());
		}
		System.out.println("-------test---------------------"+"SIZE:"+testList.size());
		for(int i=0;i<testList.size();i++){
			System.out.println(testList.get(i).getSource().getName()+"--->"+testList.get(i).getTarget().getName());
		}
		
		System.out.println("------------------------------------有差异的边-----------------------------");
		for(int i=0;i<sampleList.size();i++)
			for(int j=0;j<testList.size();j++){
				if(sampleList.get(i).equals(testList.get(j))){
					System.out.println(testList.get(i).getSource().getName()+"--->"+testList.get(i).getTarget().getName()+"-----"+i);
					tp++;
					//System.out.println(i);
				}
		}
		for(int i = 0;i<testList.size();i++){
			for(int j=0;j<sampleList.size();j++){
				if(testList.get(i).equals(sampleList.get(j))){
					tp++;
				}
			}
			
		}
		
		
		//测试多挖掘的边，与Precision相关
		System.out.println("--------------------------多挖掘的边--------------------------");
		for(int i=0;i<testList.size();i++){
			if(!sampleList.contains(testList.get(i))){
				System.out.println(testList.get(i).getSource().getName()+"--->"+testList.get(i).getTarget().getName());
			}
		}
		
		//测试少挖掘的边，与Recall相关
		System.out.println("--------------------------少挖掘的边--------------------------");
		for(int i=0;i<sampleList.size();i++){
			if(!testList.contains(sampleList.get(i))){
				System.out.println(sampleList.get(i).getSource().getName()+"--->"+sampleList.get(i).getTarget().getName());
			}
		}
		System.out.println("tp:"+tp);
		System.out.println("测试集合的边数："+testList.size());
		System.out.println("模型集合的边数："+sampleList.size());
		
		
		double Precision = 0;		//准确率
		double Recall = 0;			//召回率
		double F_Measure = 0;		//综合性指标
		
		Precision = 1.0*tp/testList.size();
		Recall = 1.0*tp/sampleList.size();
		F_Measure = 2*Precision*Recall/(Precision+Recall);
		
		System.out.println("Precision:"+Precision);
		System.out.println("Recall:"+Recall);
		System.out.println("F_Measure:"+F_Measure);
		return tp;
	}*/

}
