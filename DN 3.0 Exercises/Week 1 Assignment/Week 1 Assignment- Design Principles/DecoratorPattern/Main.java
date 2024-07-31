
public class Main {
    public static void main(String[] args) {
        Notifier notifier = new EmailNotifier();
        notifier.send("This is a basic email notification.");

        Notifier smsNotifier = new SMSNotifierDecorator(new EmailNotifier());
        smsNotifier.send("This is an email and SMS notification.");

        Notifier slackNotifier = new SlackNotifierDecorator(new EmailNotifier());
        slackNotifier.send("This is an email and Slack notification.");

        Notifier combinedNotifier = new SlackNotifierDecorator(new SMSNotifierDecorator(new EmailNotifier()));
        combinedNotifier.send("This is an email, SMS, and Slack notification.");
    }
}
