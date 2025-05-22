describe('Graph Screen interactions', () => {
  beforeEach(() => {
    cy.visit('http://localhost:8081/')
    cy.get('[data-testid="login-form"]').find('[data-testid="input-username"]').type('admin');
    cy.get('[data-testid="login-form"]').find('[data-testid="input-password"]').type('adminpass');
    cy.get('[data-testid="login-form"]').find('[data-testid="login-btn"]').click();
    cy.get('[data-testid="nav-bar"]').find('[data-testid="tab-graphs"]').click();
  });

  it('Nav bar tabs are focusable', () => {
    cy.get('[data-testid="tab-stats"]').focus().contains("STATS");
    cy.get('[data-testid="tab-text"]').focus().contains("TEXT");
    cy.get('[data-testid="tab-graphs"]').focus().contains("GRAPHS");
    cy.get('[data-testid="tab-logout"]').focus().contains("LOGOUT");
  });

  it('Check graph and buttons exist', () => {
    cy.get('[data-testid="graph-title"]').should('exist');
    cy.get('[data-testid="graph-battery-btn"]').should('exist');
    cy.get('[data-testid="graph-lin-spd-btn"]').should('exist');
    cy.get('[data-testid="graph-ang-spd-btn"]').should('exist');
  });


  it('Check functionality of buttons', () => {
    cy.get('[data-testid="graph-battery-btn"]').click()
    cy.get('[data-testid="graph-title"]').should('have.text', 'Battery Percent (%)');

    // Timeout is to avoid double clicking and showing face
    cy.wait(500);

    cy.get('[data-testid="graph-lin-spd-btn"]').click()
    cy.get('[data-testid="graph-title"]').should('have.text', 'Linear Speed (m/s)');
    cy.wait(500);


    cy.get('[data-testid="graph-ang-spd-btn"]').click()
    cy.get('[data-testid="graph-title"]').should('have.text', 'Angular Speed (rad/s)');

  });
});