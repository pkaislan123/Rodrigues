package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import model.App;

public class InsertionSort {

	/*
	 * Insertion Sort Insertion Sort ou ordena��o por inser��o � o m�todo que
	 * percorre um vetor de elementos da esquerda para a direita e � medida que
	 * avan�a vai ordenando os elementos � esquerda. Possui complexidade C(n) = O(n)
	 * no melhor caso e C(n) = O(n�) no caso m�dio e pior caso. � considerado um
	 * m�todo de ordena��o est�vel.
	 */
	
	SimpleDateFormat dateFormat = new 
            SimpleDateFormat ("dd/MM/yyyy");

	public App[] insertionSortAppName(App[] array) {
		for (int i = 1; i < array.length; i++) {
			App a = array[i];
			if(a == null) {
				break;
			}else {
			int j;
			for (j = i - 1; j >= 0 && array[j].getName().compareToIgnoreCase(a.getName()) > 0 ; j--) {
				array[j + 1] = array[j];
				array[j] = a;
			}
			}
		}
		return array;
	}
	
	public App[] insertionSortRating(App[] array) {
		for (int i = 1; i < array.length; i++) {
			App a = array[i];
			if(a == null) {
				break;
			}else {
			int j;
			for (j = i - 1; j >= 0 && Double.parseDouble(array[j].getRating()) > Double.parseDouble(a.getRating())  ; j--) {
				array[j + 1] = array[j];
				array[j] = a;
			}
			}
		}
		return array;
	}
	
	
	public App[] insertionSortLastUpdate(App[] array) throws ParseException {
		for (int i = 1; i < array.length; i++) {
			App a = array[i];
			if(a == null) {
				break;
			}else {
			int j;
			for (j = i - 1; j >= 0 && dateFormat.parse(array[j].getLast_update()).after(dateFormat.parse(a.getLast_update()))  ; j--) {
				array[j + 1] = array[j];
				array[j] = a;
			}
			}
		}
		return array;
	}
	
	
	
	public App[] insertionSortInstalls(App[] array) {
		for (int i = 1; i < array.length; i++) {
			App a = array[i];
			
			if(a == null) {
				break;
			}else {
			int j;
			for (j = i - 1; j >= 0 && Double.parseDouble(array[j].getInstalls().replace("+", "").replace(",", "").replace("\"", "")) <  Double.parseDouble(a.getInstalls().replace("+", "").replace(",", "").replace("\"", ""))  ; j--) {
				array[j + 1] = array[j];
				array[j] = a;
			}
			}
		}
		return array;
	}
	

}
