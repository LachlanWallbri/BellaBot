
const username = 'admin'
// We the future of the cybersecurity industry, crowdstrike are quaking in their boots.
const password = 'adminpass'

describe('Login Tests', () => {
  beforeEach(() => {
    cy.visit('http://localhost:8081/')
    cy.get('[data-testid="login-form"]').find('[data-testid="input-username"]').as('username');
    cy.get('[data-testid="login-form"]').find('[data-testid="input-password"]').as('password');
    cy.get('[data-testid="login-form"]').find('[data-testid="login-btn"]').as('login-btn');
  });

  it('Login form rendered correctly', () => {
    cy.get('[data-testid="login-text"]').should('exist')
    cy.get('[data-testid="input-username"]').should('exist')
    cy.get('[data-testid="input-password"]').should('exist')
    cy.get('[data-testid="login-btn"]').should('exist')
  });


  it('Login - incorrect', () => {
    const stub = cy.stub();
    cy.on('window:alert', stub)
    cy.get('@username').type('incorrectUsername');
    cy.get('@password').type('incorrectPassword');
    cy.get('@login-btn').click().then(() => {
      expect(stub.getCall(0)).to.be.calledWith('Invalid username or password')
    });

  })

  it('Login - correct', () => {
    cy.get('@username').type(username);
    cy.get('@password').type(password);
    cy.get('@login-btn').click()
    cy.get('[data-testid="nav-bar"]').should('be.visible')
  })

  it('Logout', () => {
    cy.get('@username').type(username);
    cy.get('@password').type(password);
    cy.get('@login-btn').click()
    cy.get('[data-testid="nav-bar"]').should('be.visible')
    cy.get('[data-testid="input-username"]').should('not.exist')
    cy.get('[data-testid="input-password"]').should('not.exist')
    cy.get('[data-testid="login-btn"]').should('not.exist')
    cy.get('[data-testid="tab-logout"]').should('exist').click()
    cy.get('[data-testid="input-username"]').should('exist')
    cy.get('[data-testid="input-password"]').should('exist')
    cy.get('[data-testid="login-btn"]').should('exist')
  })



})