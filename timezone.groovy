// Configure Jenkins java options
// timezone: Asia/Shanghai, America/New_York, America/Los_Angeles
// https://www.epochconverter.com/timezones
// ENV JENKINS_TIMEZONE "UTC"

def env = System.getenv();
System.setProperty('org.apache.commons.jelly.tags.fmt.timeZone', env['JENKINS_TIMEZONE']);
