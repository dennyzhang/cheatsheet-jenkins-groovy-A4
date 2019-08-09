import jenkins.model.Jenkins;
import hudson.security.GlobalMatrixAuthorizationStrategy;

if (hudson.model.User.getAll().size()==1 && hudson.model.User.getAll()[0].toString().equals('admin')) {
  List admins = [
    'admin1',
    'admin2'
  ]

  def strategy = new hudson.security.GlobalMatrixAuthorizationStrategy()
  admins.each {
    strategy.add(hudson.model.Hudson.ADMINISTER, it)
  }

  // Add read permission for all users
  strategy.add(hudson.model.Item.READ,'anonymous')
  strategy.add(hudson.model.Item.DISCOVER,'anonymous')
  strategy.add(hudson.model.Hudson.READ,'anonymous')
  strategy.add(hudson.model.Item.READ,'authenticated')
  strategy.add(hudson.model.Item.DISCOVER,'authenticated')
  strategy.add(hudson.model.Hudson.READ,'authenticated')
  Jenkins.instance.setAuthorizationStrategy(strategy)
}
