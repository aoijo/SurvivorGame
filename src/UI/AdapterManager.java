package UI;

import Enums.MapTile.MapType;
import InterfaceAdapter.MapAdapter.MapController;
import InterfaceAdapter.MapAdapter.MapPresenter;
import InterfaceAdapter.PlayerAdapter.PlayerController;
import InterfaceAdapter.PlayerAdapter.PlayerPresenter;
import InterfaceAdapter.TimeAdapter;
import InterfaceAdapter.UseCaseManager;

public class AdapterManager {
    private UseCaseManager useCaseManager;
    private PlayerPresenter playerPresenter;
    private PlayerController playerController;
    private MapPresenter mapPresenter;
    private MapController mapController;
    private TimeAdapter timeAdapter;
    private long mapSeed = 12345L;

    public AdapterManager() {
        useCaseManager = new UseCaseManager();
        playerPresenter = new PlayerPresenter(useCaseManager);
        playerController = new PlayerController(useCaseManager);
        mapPresenter = new MapPresenter(useCaseManager);
        mapController = new MapController(32, 32, MapType.ISLAND, mapSeed, useCaseManager);
        timeAdapter = new TimeAdapter(useCaseManager);
    }
}
