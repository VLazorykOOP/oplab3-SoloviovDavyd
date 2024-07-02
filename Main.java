// DataSource.java
interface DataSource {
    String fetchData(); // Інтерфейс для отримання даних з різних джерел
}

// DatabaseDataSource.java
class DatabaseDataSource implements DataSource {
    @Override
    public String fetchData() {
        return "Data from database"; // Реалізація отримання даних з бази даних
    }
}

// FileDataSource.java
class FileDataSource implements DataSource {
    @Override
    public String fetchData() {
        return "Data from file"; // Реалізація отримання даних з файлу
    }
}

// Report.java
abstract class Report {
    protected DataSource dataSource; // Джерело даних для звіту

    public Report(DataSource dataSource) {
        this.dataSource = dataSource; // Конструктор для ініціалізації джерела даних
    }

    public void generateReport() {
        fetchData(); // Отримання даних з джерела
        formatReport(); // Форматування звіту (реалізується в підкласах)
        printReport(); // Друк звіту
    }

    protected void fetchData() {
        System.out.println("Fetching data: " + dataSource.fetchData()); // Виведення повідомлення про отримання даних
    }

    protected abstract void formatReport(); // Абстрактний метод для форматування звіту

    protected void printReport() {
        System.out.println("Printing report"); // Виведення повідомлення про друк звіту
    }
}

// PDFReport.java
class PDFReport extends Report {
    public PDFReport(DataSource dataSource) {
        super(dataSource); // Конструктор класу PDFReport, ініціалізує Report з вказаним джерелом
    }

    @Override
    protected void formatReport() {
        System.out.println("Formatting PDF Report"); // Форматування звіту у форматі PDF
    }
}

// HTMLReport.java
class HTMLReport extends Report {
    public HTMLReport(DataSource dataSource) {
        super(dataSource); // Конструктор класу HTMLReport, ініціалізує Report з вказаним джерелом
    }

    @Override
    protected void formatReport() {
        System.out.println("Formatting HTML Report"); // Форматування звіту у форматі HTML
    }
}

// ReportFactory.java
abstract class ReportFactory {
    public abstract Report createReport(DataSource dataSource); // Абстрактний метод для створення звіту
}

// PDFReportFactory.java
class PDFReportFactory extends ReportFactory {
    @Override
    public Report createReport(DataSource dataSource) {
        return new PDFReport(dataSource); // Створення PDF-звіту
    }
}

// HTMLReportFactory.java
class HTMLReportFactory extends ReportFactory {
    @Override
    public Report createReport(DataSource dataSource) {
        return new HTMLReport(dataSource); // Створення HTML-звіту
    }
}

// Main.java
public class Main {
    public static void main(String[] args) {
        DataSource dbSource = new DatabaseDataSource(); // Створення джерела даних з бази даних
        DataSource fileSource = new FileDataSource(); // Створення джерела даних з файлу

        ReportFactory pdfFactory = new PDFReportFactory(); // Створення фабрики PDF-звітів
        Report pdfReportFromDb = pdfFactory.createReport(dbSource); // Створення PDF-звіту з бази даних
        pdfReportFromDb.generateReport(); // Генерація PDF-звіту

        Report pdfReportFromFile = pdfFactory.createReport(fileSource); // Створення PDF-звіту з файлу
        pdfReportFromFile.generateReport(); // Генерація PDF-звіту

        ReportFactory htmlFactory = new HTMLReportFactory(); // Створення фабрики HTML-звітів
        Report htmlReportFromDb = htmlFactory.createReport(dbSource); // Створення HTML-звіту з бази даних
        htmlReportFromDb.generateReport(); // Генерація HTML-звіту

        Report htmlReportFromFile = htmlFactory.createReport(fileSource); // Створення HTML-звіту з файлу
        htmlReportFromFile.generateReport(); // Генерація HTML-звіту
    }
}
