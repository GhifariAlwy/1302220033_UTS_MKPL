package lib;

public class TaxFunction {

	
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */
	
	
	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking,
                             int deductible, boolean isMarried, int numberOfChildren) {
    	// Validasi input
    	if (numberOfMonthWorking > 12) {
        	System.err.println("More than 12 month working per year");
    	}

    	// Konstanta pajak
    	final double TAX_RATE = 0.05;
    	final int NON_TAXABLE_BASE = 54000000;
    	final int MARRIED_DEDUCTION = 4500000;
    	final int PER_CHILD_DEDUCTION = 1500000;
    	final int MAX_CHILDREN = 3;

    	// Hitung komponen pajak
    	int actualChildren = Math.min(numberOfChildren, MAX_CHILDREN);
   	int totalIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
    	int taxableIncome = totalIncome - deductible;
    
    	// Hitung pengurangan pajak
    	int taxDeduction = NON_TAXABLE_BASE;
    	if (isMarried) {
        	taxDeduction += MARRIED_DEDUCTION + (actualChildren * PER_CHILD_DEDUCTION);
    	}

    	// Hitung pajak akhir
    	int tax = (int) Math.round(TAX_RATE * (taxableIncome - taxDeduction));
    	return Math.max(tax, 0);
	}
	
}
