
const chargeStates = [
  "Idle", "Charging", "ChargeFull", "ChargeErrorContact", "ChargeErrorElectric",
  "ErrorBatteryPackComm", "ErrorOverVolt", "ErrorOverElectric", "ErrorOverTemperature",
  "ErrorLowTemperature", "Retain", "ChargingUsePile", "ChargeFullUsePile",
  "StopChargeUsePile", "ErrorOverTime", "Disconnected"
];

const wheelErrorStates = [
  "SchOver", "Stall", "SpeedOverOld", "MotorFly",
  "SpdFollowErr", "SpdLose", "JY01Close", "Preseve",
  "HardwareCurrentOver", "MotherCurrentOver", "MotherVoltageOver", "MotherVoltageLow",
  "TemperatureOver", "EncoderError", "ABZBreak", "HallError",
  "SpeedOver", "MotorStuck", "MotorOver", "SpeedFlowDeviation",
  "CANCmdLose", "CANBreak", "CoincidentAxisError", "InternalError",
  "CurrentZeroDriftError", "TaskLoadOver", "EepromError", "OutLosePhase",
  "PhaseCurOver", "MotorTempOver", "MosTempOver", "MosSoftStartError",
  "BumpSwitchReset", "EmergencyKeyPressed", "None"
];


const chargeStatesOrStatement = chargeStates.join("|");
const wheelErrorOrStatement = wheelErrorStates.join("|");

describe('Stats Screen interactions', () => {
  beforeEach(() => {
    cy.visit('http://localhost:8081/')
    cy.get('[data-testid="login-form"]').find('[data-testid="input-username"]').type('admin');
    cy.get('[data-testid="login-form"]').find('[data-testid="input-password"]').type('adminpass');
    cy.get('[data-testid="login-form"]').find('[data-testid="login-btn"]').click()
    cy.get('[data-testid="nav-bar"]').find('[data-testid="tab-stats"]').click()
  });

  it('Nav bar tabs are focusable', () => {
    cy.get('[data-testid="tab-stats"]').focus().contains("STATS")
    cy.get('[data-testid="tab-text"]').focus().contains("TEXT")
    cy.get('[data-testid="tab-graphs"]').focus().contains("GRAPHS")
    cy.get('[data-testid="tab-logout"]').focus().contains("LOGOUT")
  });

  it('General stats exist', () => {
    cy.get('[data-testid="stat-connected"]').contains(new RegExp('CANBus Connected: (FALSE|TRUE)'))
    cy.get('[data-testid="stat-charge-state"]').contains(new RegExp(`Last Charge State: (${chargeStatesOrStatement})`))
    cy.get('[data-testid="stat-battery"]').contains(new RegExp('Battery Percent: (100|[1-9]?[0-9])%'))


    cy.get('[data-testid="stat-instant-gyro-x"]').contains(new RegExp('x: -?\\d+\\.\\d{2} rad/s'))
    cy.get('[data-testid="stat-instant-gyro-y"]').contains(new RegExp('y: -?\\d+\\.\\d{2} rad/s'))
    cy.get('[data-testid="stat-instant-gyro-z"]').contains(new RegExp('z: -?\\d+\\.\\d{2} rad/s'))

    cy.get('[data-testid="stat-acc-gyro-x"]').contains(new RegExp('x: -?\\d+\\.\\d{2} rad/s'))
    cy.get('[data-testid="stat-acc-gyro-y"]').contains(new RegExp('y: -?\\d+\\.\\d{2} rad/s'))
    cy.get('[data-testid="stat-acc-gyro-z"]').contains(new RegExp('z: -?\\d+\\.\\d{2} rad/s'))

    cy.get('[data-testid="stat-wheel-err-left"]').contains(new RegExp(`Left Wheel Error: ${wheelErrorOrStatement}`))
    cy.get('[data-testid="stat-wheel-err-right"]').contains(new RegExp(`CANBus Connected: ${wheelErrorOrStatement}`))

    cy.get('[data-testid="stat-lin-speed"]').contains(new RegExp('Linear Speed: -?\\d+\\.\\d{2} m/s'))
    cy.get('[data-testid="stat-ang-speed"]').contains(new RegExp('Angular Speed: -?\\d+\\.\\d{2} rad/s'))

    cy.get('[data-testid="stat-accel-slider"]').should('exist');
    cy.get('[data-testid="stat-decel-slider"]').should('exist');

  });
});