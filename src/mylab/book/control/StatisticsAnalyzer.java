package mylab.book.control;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import mylab.book.entity.Magazine;
import mylab.book.entity.Novel;
import mylab.book.entity.Publication;
import mylab.book.entity.ReferenceBook;

public class StatisticsAnalyzer {

	
	public Map<String, Double> calculateAveragePriceByType(Publication[] publications) {
		Map<String, Integer> totalPrice = new HashMap<>();
		Map<String, Integer> count = new HashMap<>();

		for (Publication pub : publications) {
			String type = getPublicationType(pub);
			totalPrice.put(type, totalPrice.getOrDefault(type, 0) + pub.getPrice());
			count.put(type, count.getOrDefault(type, 0) + 1);
		}

		Map<String, Double> average = new HashMap<>();
		for (String type : totalPrice.keySet()) {
			average.put(type, (double) totalPrice.get(type) / count.get(type));
		}
		return average;
	}

	
	public Map<String, Double> calculatePublicationDistribution(Publication[] publications) {
		Map<String, Integer> count = new HashMap<>();

		for (Publication pub : publications) {
			String type = getPublicationType(pub);
			count.put(type, count.getOrDefault(type, 0) + 1);
		}

		Map<String, Double> distribution = new HashMap<>();
		for (String type : count.keySet()) {
			distribution.put(type, (double) count.get(type) / publications.length * 100);
		}
		return distribution;
	}

	
	public double calculatePublicationRatioByYear(Publication[] publications, String year) {
		int count = 0;
		for (Publication pub : publications) {
			
			if (pub.getPublishDate().substring(0, 4).equals(year)) {
				count++;
			}
		}
		return (double) count / publications.length * 100;
	}

	
	private String getPublicationType(Publication pub) {
		if (pub instanceof Novel) {
			return "소설";
		} else if (pub instanceof Magazine) {
			return "잡지";
		} else if (pub instanceof ReferenceBook) {
			return "참고서";
		}
		return "기타";
	}

	
	public void printStatistics(Publication[] publications) {
		DecimalFormat df = new DecimalFormat("#,###.##");

		System.out.println("===== 출판물 통계 분석 =====");

		System.out.println("1. 타입별 평균 가격:");
		Map<String, Double> average = calculateAveragePriceByType(publications);
		for (String type : average.keySet()) {
			System.out.println("   - " + type + ": " + df.format(average.get(type)) + "원");
		}

		System.out.println("2. 출판물 유형 분포:");
		Map<String, Double> distribution = calculatePublicationDistribution(publications);
		for (String type : distribution.keySet()) {
			System.out.println("   - " + type + ": " + df.format(distribution.get(type)) + "%");
		}

		double ratio = calculatePublicationRatioByYear(publications, "2007");
		System.out.println("3. 2007년에 출판된 출판물 비율: " + df.format(ratio) + "%");
	}
}