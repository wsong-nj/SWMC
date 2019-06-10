package org.nust.wf.util;

import java.util.ArrayList;

import org.nust.wf.basicstruct.Edge;


public class MiningEffective {
	
	public double precision;	//�ٻ���
	public double recall;		//׼ȷ��
	public double f_Measure;	//�ۺ�����ָ��
	
	
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
		int tp=0;	//��������ȷ�ı�
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
		
		int tp=0;	//��������ȷ�ı�
		
		//��ӡ�ھ�����ı�
		System.out.println("-------sample---------------------"+"SIZE:"+sampleList.size());
		for(int i=0;i<sampleList.size();i++){
			System.out.println(sampleList.get(i).getSource().getName()+"--->"+sampleList.get(i).getTarget().getName());
		}
		System.out.println("-------test---------------------"+"SIZE:"+testList.size());
		for(int i=0;i<testList.size();i++){
			System.out.println(testList.get(i).getSource().getName()+"--->"+testList.get(i).getTarget().getName());
		}
		
		System.out.println("------------------------------------�в���ı�-----------------------------");
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
		
		
		//���Զ��ھ�ıߣ���Precision���
		System.out.println("--------------------------���ھ�ı�--------------------------");
		for(int i=0;i<testList.size();i++){
			if(!sampleList.contains(testList.get(i))){
				System.out.println(testList.get(i).getSource().getName()+"--->"+testList.get(i).getTarget().getName());
			}
		}
		
		//�������ھ�ıߣ���Recall���
		System.out.println("--------------------------���ھ�ı�--------------------------");
		for(int i=0;i<sampleList.size();i++){
			if(!testList.contains(sampleList.get(i))){
				System.out.println(sampleList.get(i).getSource().getName()+"--->"+sampleList.get(i).getTarget().getName());
			}
		}
		System.out.println("tp:"+tp);
		System.out.println("���Լ��ϵı�����"+testList.size());
		System.out.println("ģ�ͼ��ϵı�����"+sampleList.size());
		
		
		double Precision = 0;		//׼ȷ��
		double Recall = 0;			//�ٻ���
		double F_Measure = 0;		//�ۺ���ָ��
		
		Precision = 1.0*tp/testList.size();
		Recall = 1.0*tp/sampleList.size();
		F_Measure = 2*Precision*Recall/(Precision+Recall);
		
		System.out.println("Precision:"+Precision);
		System.out.println("Recall:"+Recall);
		System.out.println("F_Measure:"+F_Measure);
		return tp;
	}*/

}
