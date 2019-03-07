import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class ExampleLoan {

    public static final LoanApplication LOAN_APPLICATION = new LoanApplication();

    static {
        final LoanDetails loanDetails = new LoanDetails();
        loanDetails.setMount(10000.00);
        loanDetails.setStartDate(LocalDate.of(2018,1,15));
        loanDetails.setEndDate(LocalDate.of(2020, 2,16));

        final List<Job> jobs = new ArrayList<>();
        Job job = new Job();
        job.setTitle("Freelance Developer");
        job.setAnnualIncome(18000);
        job.setYearsActive(3);
        jobs.add(job);

        job = new Job();
        job.setTitle("Part Time Developer");
        job.setAnnualIncome(4000);
        job.setYearsActive(8);
        jobs.add(job);

        job = new Job();
        job.setTitle("PluralSight Author");
        job.setAnnualIncome(13000);
        job.setYearsActive(1);
        jobs.add(job);

        LOAN_APPLICATION.setName("Mrs Joan Smith");
        LOAN_APPLICATION.setPurposeOfLoan("To build an extension to my house");
        LOAN_APPLICATION.setLoanDetails(loanDetails);
        LOAN_APPLICATION.setJobs(jobs);
    }
}

class LoanApplication {

    private String name;
    private String purposeOfLoan;
    private LoanDetails loanDetails;
    private List<Job> jobs;

    public LoanApplication() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPurposeOfLoan(String purposeOfLoan) {
        this.purposeOfLoan = purposeOfLoan;
    }

    public void setLoanDetails(LoanDetails loanDetails) {
        this.loanDetails = loanDetails;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public String getName() {
        return name;
    }

    public String getPurposeOfLoan() {
        return purposeOfLoan;
    }

    public LoanDetails getLoanDetails() {
        return loanDetails;
    }

    public List<Job> getJobs() {
        return jobs;
    }
}

class LoanDetails{
    private double mount;
    private LocalDate startDate;
    private LocalDate endDate;

    public LoanDetails() {
    }

    public void setMount(double mount) {
        this.mount = mount;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getMount() {
        return mount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}

class Job {
    private String title;
    private int annualIncome;
    private int yearsActive;

    public Job() {

    }

    public String getTitle() {
        return title;
    }

    public int getAnnualIncome() {
        return annualIncome;
    }

    public int getYearsActive() {
        return yearsActive;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAnnualIncome(int annualIncome) {
        this.annualIncome = annualIncome;
    }

    public void setYearsActive(int yearsActive) {
        this.yearsActive = yearsActive;
    }
}

