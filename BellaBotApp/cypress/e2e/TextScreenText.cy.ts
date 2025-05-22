describe('Text Screen interactions', () => {
  beforeEach(() => {
    cy.visit('http://localhost:8081/')
    cy.get('[data-testid="login-form"]').find('[data-testid="input-username"]').type('admin');
    cy.get('[data-testid="login-form"]').find('[data-testid="input-password"]').type('adminpass');
    cy.get('[data-testid="login-form"]').find('[data-testid="login-btn"]').click()

  })

  it('Nav bar tabs are focusable', () => {
    cy.get('[data-testid="tab-stats"]').focus().contains("STATS")
    cy.get('[data-testid="tab-text"]').focus().contains("TEXT")
    cy.get('[data-testid="tab-graphs"]').focus().contains("GRAPHS")
    cy.get('[data-testid="tab-logout"]').focus().contains("LOGOUT")
  })

  it('Text tab is typeable', () => {
    cy.get('[data-testid="tab-text"]').focus().contains("TEXT").click()
    cy.get('[data-testid="text-input"]')
      .should('exist')
      .should('not.include.text', 'I thank god I never signed my life away')
      .focus()
      .type('I thank god I never signed my life away')
      .contains('I thank god I never signed my life away')
  })





})