require 'capybara'
require 'rspec'
require 'selenium-webdriver'
require 'capybara/poltergeist'

Capybara.register_driver :poltergeist do |app|
	Capybara::Poltergeist::Driver.new(app, {js_errors: false, port:8899, phantomjs_options:['--proxy-type=none'], timeout:15})
end
Capybara.javascript_driver = :poltergeist

Capybara.configure do |config|
	config.javascript_driver = :poltergeist
    config.default_driver = :poltergeist
	config.app_host = "http://localhost:8899"
end

RSpec.configure do |config|
	config.include Capybara::DSL
end
