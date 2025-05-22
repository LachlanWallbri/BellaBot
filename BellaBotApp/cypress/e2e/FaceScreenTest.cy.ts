describe('Face Screen interactions', () => {
  beforeEach(() => {
    cy.visit('http://localhost:8081/')
    cy.get('[data-testid="login-form"]').find('[data-testid="input-username"]').type('admin');
    cy.get('[data-testid="login-form"]').find('[data-testid="input-password"]').type('adminpass');
    cy.get('[data-testid="login-form"]').find('[data-testid="login-btn"]').click();
    cy.wait(500);
  });


  it('Reach face screen from graphs screen', () => {
    cy.get('[data-testid="nav-bar"]').find('[data-testid="tab-graphs"]').click();
    cy.wait(500);

    cy.get('[data-testid="graph-title"]').click();
    cy.get('[data-testid="graph-title"]').click();

    cy.get('[data-testid="face-root"]').should('exist');
  });

  it('Reach face screen from stats screen', () => {
    cy.get('[data-testid="nav-bar"]').find('[data-testid="tab-stats"]').click();
    cy.wait(500);

    cy.get('[data-testid="stat-connected"]').click();
    cy.get('[data-testid="stat-connected"]').click();

    cy.get('[data-testid="face-root"]').should('exist');
  });

  it('Nav bar tabs are NOT existing on face screen', () => {
    cy.get('[data-testid="nav-bar"]').find('[data-testid="tab-graphs"]').click();
    cy.wait(500);

    cy.get('[data-testid="graph-title"]').click();
    cy.get('[data-testid="graph-title"]').click();

    cy.get('[data-testid="tab-stats"]').should('not.exist');
    cy.get('[data-testid="tab-text"]').should('not.exist');
    cy.get('[data-testid="tab-graphs"]').should('not.exist');
    cy.get('[data-testid="tab-logout"]').should('not.exist');
  });

});