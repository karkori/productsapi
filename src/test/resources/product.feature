Feature: Products

  Scenario Outline: Retrieve products
    Given I have valid params, saleUnits: <saleUnits> and stock: <stock>
    When I call the getProducts service
    Then I assert that I will get a sorted product list
    Examples:
      | saleUnits | stock |
      | 2         | 1     |
      | 2         | 15    |
      | 0         | 10    |
      | 10        | 6     |

  Scenario Outline: Retrieve products with bad params
    Given I have invalid params, saleUnits: <saleUnits> and stock: <stock>
    When I call the getProducts service
    Then I assert that I will get an invalid params exception
    Examples:
      | saleUnits | stock |
      | 2         | 1     |
      | 2         | 15    |